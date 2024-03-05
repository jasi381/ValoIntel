package com.jasmeet.valorantapi.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.valorantapi.state.State
import com.jasmeet.valorantapi.ui.theme.sans
import com.jasmeet.valorantapi.utils.Utils
import com.jasmeet.valorantapi.viewModels.AgentsViewModel
import java.net.URLDecoder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AgentDetailScreen(navHostController: NavHostController, agentUUID: String?) {

    val decodedUUid = URLDecoder.decode(agentUUID, "UTF-8")
    val interactionSource = remember { MutableInteractionSource() }

    val vm: AgentsViewModel = hiltViewModel()
    val agentDetails by vm.agentDetails.observeAsState(State.Loading)

    LaunchedEffect(true) {
        vm.fetchAgentData(decodedUUid)

    }

    Scaffold(
        topBar = {}

    ) {


        when (val state = agentDetails) {
            is State.Loading -> {
                Box(
                    Modifier
                        .fillMaxSize()

                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            is State.Error -> {
                Box(
                    Modifier
                        .fillMaxSize()


                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = state.message.toString())

                    }

                }
            }

            is State.Success -> {

                val colorList = state.data.data.backgroundGradientColors.map { color ->
                    Color(
                        android.graphics.Color.parseColor(
                            Utils.formatColorString(color)
                        )
                    )
                }

                val agentData = state.data.data

                val expandedStates = remember { mutableStateListOf<Boolean>().apply {
                    repeat(agentData.abilities.size) {
                        add(false)
                    }
                }}

                val imgList = listOf(
                    agentData.bustPortrait,
                    agentData.displayIcon,
                )



                Column {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenHeightDp.dp * 0.33f)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = colorList
                                )
                            )
                    ) {
                        Box(
                            Modifier
                                .statusBarsPadding()
                                .fillMaxSize()
                        ) {
                            IconButton(
                                onClick = {
                                    navHostController.popBackStack()
                                },
                                modifier = Modifier
                                    .align(Alignment.TopStart)

                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }

                            AsyncImage(
                                model = state.data.data.fullPortraitV2,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(bottom = 25.dp)
                                    .align(Alignment.Center)
                                    .fillMaxSize()
                                    .scale(1.25f)
                            )
                        }

                    }

                    Column(
                        Modifier
                            .weight(1f)

                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .background(Color(0xff101118))
                            .padding(horizontal = 16.dp)
                            .navigationBarsPadding()
                    ) {


                        Text(
                            text = agentData.displayName,
                            fontFamily = sans,
                            modifier = Modifier
                                .padding(top = 16.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xffedf0ef)
                        )

                        Text(
                            text = agentData.description,
                            fontFamily = sans,
                            modifier = Modifier
                                .padding(vertical = 16.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xffedf0ef)
                        )

                        Text(
                            text = "Abilities",
                            fontFamily = sans,
                            modifier = Modifier.padding(bottom = 16.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xffedf0ef)
                        )

                        agentData.abilities.forEachIndexed {index ,ability ->

                            val isExpanded2 = expandedStates[index]
                            Row(
                                Modifier
                                    .padding(bottom = 8.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {

                                Box(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                        .background(Color(0xff292939))
                                ) {
                                    AsyncImage(
                                        model = ability.displayIcon?:"https://cdn-icons-png.flaticon.com/512/2748/2748558.png",
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .size(48.dp)
                                            .align(Alignment.Center)
                                    )

                                }

                                Column(
                                    Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = ability.displayName,
                                        fontFamily = sans,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W400,
                                        color = Color(0xffedf0ef),

                                        )

                                    AnimatedVisibility(visible = isExpanded2,
                                        enter = expandVertically(
                                            expandFrom = Alignment.Top,
                                            animationSpec = spring(stiffness = Spring.StiffnessLow)
                                        ),
                                        exit = shrinkVertically(
                                            shrinkTowards = Alignment.Top,
                                            animationSpec = spring(stiffness = Spring.StiffnessLow)
                                        )
                                    ) {

                                        Text(
                                            text = ability.description.trim(),
                                            fontFamily = sans,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = Color(0xff9e9eb8)
                                        )

                                    }

                                    Text(
                                        text = if (isExpanded2) "Show Less" else "Show More",
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .clickable(
                                                interactionSource = interactionSource,
                                                indication = null
                                            ) {
                                                expandedStates[index] = !isExpanded2
                                            }
                                            .padding(vertical = 8.dp, horizontal = 5.dp),
                                        fontFamily = sans,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.W400,
                                        color = Color(0xffedf0ef)
                                    )
                                }


                            }

                        }
                        Text(
                            text = "Images",
                            fontFamily = sans,
                            modifier = Modifier.padding(vertical = 16.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xffedf0ef)
                        )


                        LazyRow(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(imgList){
                                AsyncImage(
                                    model = it,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(180.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = colorList
                                            )
                                        )
                                        .padding(10.dp),
                                )
                            }

                        }

                        Text(
                            text = "Role",
                            fontFamily = sans,
                            modifier = Modifier.padding(vertical = 16.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xffedf0ef)
                        )

                        Row(Modifier.fillMaxWidth()) {

                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(Color(0xff292939))
                            ) {
                                AsyncImage(
                                    model = agentData.role.displayIcon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(48.dp)
                                        .align(Alignment.Center)
                                )

                            }
                            Column(
                                Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = agentData.role.displayName,
                                    fontFamily = sans,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W400,
                                    color = Color(0xffedf0ef),

                                    )

                                Text(
                                    text = agentData.role.description,
                                    fontFamily = sans,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.W400,
                                    color = Color(0xff9e9eb8)
                                )
                            }
                        }
                    }
                }

            }

        }

    }


}