package com.jasmeet.valorantapi.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.presentation.viewModels.CurrenciesViewModel

@Composable
fun CurrenciesScreen( navHostController: NavHostController) {

    val agentsViewModel: CurrenciesViewModel = hiltViewModel()
    val apiResponse by agentsViewModel.currencyApiResponse.observeAsState(State.Loading)

    LaunchedEffect(true) {
        agentsViewModel.fetchCurrencies()

    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Currencies",
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
                    LoaderComponent(
                        modifier = Modifier
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
            is State.Success ->{
                Column(modifier = Modifier.padding(innerPadding)) {
                    state.data.forEach {
                        Text(text = it.uuid)
                    }
                }


            }            }
        }

}