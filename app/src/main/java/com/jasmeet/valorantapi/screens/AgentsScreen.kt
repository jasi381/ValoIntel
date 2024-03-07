package com.jasmeet.valorantapi.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.valorantapi.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.appComponents.animatedBorder
import com.jasmeet.valorantapi.state.State
import com.jasmeet.valorantapi.ui.theme.sans
import com.jasmeet.valorantapi.ui.theme.valorantFont
import com.jasmeet.valorantapi.utils.Utils
import com.jasmeet.valorantapi.viewModels.AgentsViewModel
import java.net.URLEncoder

@Composable
fun AgentsScreen(navHostController: NavHostController) {

    val agentsViewModel: AgentsViewModel = hiltViewModel()
    val apiResponse by agentsViewModel.agentsApiResponse.observeAsState(State.Loading)

    LaunchedEffect(true) {
        agentsViewModel.fetchAgents()

    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Agents",
                enableBackButton = true,
                onBackClick = {
                    navHostController.popBackStack()
                }
            )
        }
    ) { innerPadding ->


        when (val state = apiResponse) {
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
                        .padding(innerPadding)

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
                        .background(Color(0xff101118))
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp)
                        .padding(innerPadding),
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
                        Column {
                            Surface(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .animatedBorder(
                                        borderColors = listOf(
                                            Color.White,
                                            Color.Green,

                                        ),
                                        backgroundColor = Color.White,
                                        borderWidth = 1.dp
                                    )
                                    .size(180.dp),
                                shape = MaterialTheme.shapes.large,
                                onClick = {
                                    val encodedUUid = URLEncoder.encode(it.uuid, "UTF-8")
                                    navHostController.navigate(
                                        Screens.AgentDetailScreen.passUuid(
                                            encodedUUid
                                        )
                                    )
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
                                    AsyncImage(
                                        model = it.fullPortrait,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxSize(),
                                    )
                                }
                            }

                            Text(
                                text = it.displayName.toString(),
                                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp),
                                fontFamily = valorantFont,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xffedf0ef)
                            )
                        }
                    }
                }

            }
        }
    }
}