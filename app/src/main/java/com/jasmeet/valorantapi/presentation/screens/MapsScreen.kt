package com.jasmeet.valorantapi.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.appcomponents.animatedBorder
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.presentation.theme.valorantFont
import com.jasmeet.valorantapi.presentation.viewModels.MapsViewModel
import java.net.URLEncoder

@Composable
fun MapsScreen(navHostController: NavHostController) {

    val mapsViewModel : MapsViewModel = hiltViewModel()
    val apiResponse by mapsViewModel.mapsApiResponse.observeAsState(State.Loading)

    var sortAscending by rememberSaveable { mutableStateOf(true) }


    LaunchedEffect(true) {
        mapsViewModel.fetchMaps()
    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Maps",
                enableBackButton = true,
                onBackClick = {
                    navHostController.popBackStack()
                },
                enableAction = true,
                onActionClick = {
                    sortAscending = !sortAscending

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
                    LoaderComponent(modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center),
                        rawRes = R.raw.loader
                    )
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

                val sortedData = if(sortAscending){
                    state.data
                }else{
                    state.data.sortedBy {
                        it.displayName
                    }
                }


                LazyColumn(
                    Modifier
                        .background(Color(0xff101118))
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    item {
                        Spacer(modifier =Modifier.height(10.dp))
                    }
                    items(sortedData){mapsData ->
                        Column {
                            Surface(
                                shape = MaterialTheme.shapes.medium,
                                onClick = {
                                    val encodedUUid = URLEncoder.encode(mapsData.uuid, "UTF-8")
                                    navHostController.navigate(
                                        Screens.MapDetailsScreen.passUuid(
                                            encodedUUid
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 10.dp)
                                    .fillMaxWidth()
                                    .height(110.dp)
                                    .animatedBorder(
                                        borderColors = listOf(
                                            Color(0xffc7f458),
                                            Color(0xff3a7233)
                                        ),
                                        backgroundColor = Color.White,
                                    )
                            ) {
                                Box{
                                    AsyncImage(
                                        model = mapsData.listViewIcon,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(MaterialTheme.shapes.medium)
                                            .align(Alignment.Center),
                                        contentScale = ContentScale.FillBounds
                                    )

                                }

                            }
                            Text(
                                text = mapsData.displayName,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .offset(
                                        y = (-5).dp
                                    ),
                                fontFamily = valorantFont,
                                fontSize = 18.sp,
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