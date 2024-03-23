package com.jasmeet.valorantapi.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.valorantapi.appComponents.LoaderComponent
import com.jasmeet.valorantapi.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.appComponents.animatedBorder
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.theme.valorantFont
import com.jasmeet.valorantapi.presentation.viewModels.WeaponsViewModel
import java.net.URLEncoder

@Composable
fun WeaponsScreen(navHostController: NavHostController) {

    val weaponsViewModel: WeaponsViewModel = hiltViewModel()
    val apiResponse by weaponsViewModel.weaponsApiResponse.observeAsState(State.Loading)

    val colors = listOf(
        Color(0xff978d83),
        Color(0xffd9d2c5),
        Color(0xffbfb5a9)
    )
    var sortAscending by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        weaponsViewModel.fetchWeapons()
    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Arsenal",
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
                    LoaderComponent(modifier = Modifier.size(150.dp).align(Alignment.Center))
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

                val sortedData = if (sortAscending) {
                    state.data
                } else {
                    state.data.sortedBy { it.displayName }
                }
                LazyColumn(
                    Modifier
                        .background(Color(0xff101118))
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {

                    items(sortedData){ weaponsData ->
                        Column {
                            Surface(
                                shape = MaterialTheme.shapes.medium,
                                onClick = {
                                    val encodedUUid = URLEncoder.encode(weaponsData.uuid, "UTF-8")
                                    navHostController.navigate(
                                        Screens.WeaponDetailsScreen.passUuid(
                                            encodedUUid
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 10.dp)
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .animatedBorder(
                                        borderColors = listOf(
                                            Color(0xffc7f458),
                                            Color(0xff3a7233)
                                        ),
                                        backgroundColor = Color.White,
                                    )
                            ) {
                                Box(
                                    Modifier.background(
                                        brush = Brush.horizontalGradient(
                                            colors = colors,
                                            tileMode = TileMode.Mirror
                                        )
                                    )
                                ) {
                                    AsyncImage(
                                        model = weaponsData.skins.first().chromas[0].fullRender,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(
                                                vertical = 10.dp,
                                                horizontal = 15.dp
                                            )
                                            .fillMaxSize()
                                            .clip(MaterialTheme.shapes.medium)
                                            .align(Alignment.Center)
                                    )

                                }

                            }
                            Text(
                                text = weaponsData.displayName,
                                modifier = Modifier.align(Alignment.CenterHorizontally).offset(
                                    y= (-5).dp
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