package com.jasmeet.valorantapi.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.valorantapi.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.state.State
import com.jasmeet.valorantapi.viewModels.WeaponsViewModel

@Composable
fun WeaponsScreen(navHostController: NavHostController) {

    val weaponsViewModel: WeaponsViewModel = hiltViewModel()
    val apiResponse by weaponsViewModel.weaponsApiResponse.observeAsState(State.Loading)

    LaunchedEffect(key1 = true) {
        weaponsViewModel.fetchWeapons()
    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Weapons",
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
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                  items(state.data){ weaponsData ->
                      OutlinedCard(

                          onClick = {
                                    Log.d("WeaponsScreen", "${state.data.size}")
                          },
                          modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth().height(80.dp)
                      ) {
                          AsyncImage(
                              model = weaponsData.skins.first().chromas.first().fullRender,
                              contentDescription =null ,
                              contentScale = ContentScale.FillBounds,
                              modifier = Modifier.padding(
                                  vertical = 10.dp,
                                  horizontal = 20.dp
                                  ).fillMaxSize().align(Alignment.CenterHorizontally)
                          )

                      }
                  }

                }

            }
        }


    }


}