package com.jasmeet.valorantapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jasmeet.valorantapi.room.AgentsDatabase
import com.jasmeet.valorantapi.state.State
import com.jasmeet.valorantapi.ui.theme.ValorantApiTheme
import com.jasmeet.valorantapi.ui.theme.valorantFont
import com.jasmeet.valorantapi.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            ValorantApiTheme {
                Greeting()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {

    val context = LocalContext.current
    val vm :AgentsViewModel = hiltViewModel()
    val apiResponse by vm.apiResponse.observeAsState(State.Loading)
    val agentDetails by vm.agentDetails.observeAsState(State.Loading)

    val database = AgentsDatabase.getInstance(context = context)
    val agentsDao = database.agentsDao()

    val coroutineSCope = rememberCoroutineScope()

    LaunchedEffect(true) {
        vm.fetchAgents()

    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xff0b1514)
                ),

                title = {
                    Text(
                        text = "Valo Intel",
                        fontFamily = valorantFont,
                        fontSize = 25.sp,
                        color = Color(0xffff4654)
                    )
                },
            )
        }
    ) {
        when (val state = apiResponse) {
            is State.Loading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            is State.Error -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = state.message.toString())

                    }

                }
            }

            is State.Success -> {

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xffeb953f),
                                    Color(0xff892c1b),
                                    Color(0xff211d21),
                                    Color(0xff282b38),
                                )
                            )
                        )
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding(), start = 8.dp, end = 8.dp)
                        .navigationBarsPadding(),
                    horizontalArrangement = Arrangement.spacedBy(25.dp),
                    verticalItemSpacing = 25.dp
                ) {

                    val distinctAgents =
                        state.data.reversed().distinctBy { it.displayName }.reversed()
                            .sortedBy {
                                it.displayName
                            }

                    items(distinctAgents) {

                        val colorList = it.backgroundGradientColors.map { color ->
                            Color(
                                android.graphics.Color.parseColor(
                                    Utils.formatColorString(color)
                                )
                            )
                        }
                        Surface(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(180.dp),

                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            shape = MaterialTheme.shapes.large,
                            onClick = {

                                it.uuid?.let { it1 -> vm.fetchAgentDate(it1) }

//                                coroutineSCope.launch {
//                                    agentsDao.insertAgents(
//                                        AgentsEntity(
//                                            uuid = it.uuid,
//                                            displayName = it.displayName,
//                                            abilities = it.abilities,
//                                            backgroundGradientColors = it.backgroundGradientColors
//                                        )
//                                    )
//                                }

                            },
                            color = Color.Transparent
                        ) {
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = colorList
                                        )
                                    ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                it.displayName?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                                AsyncImage(model = it.fullPortrait, contentDescription = null)

                            }

                        }
                    }


                }

            }
        }

    }


}

