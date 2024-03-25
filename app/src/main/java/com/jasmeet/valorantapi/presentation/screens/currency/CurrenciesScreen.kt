package com.jasmeet.valorantapi.presentation.screens.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.presentation.theme.sans
import com.jasmeet.valorantapi.presentation.viewModels.CurrenciesViewModel
import kotlin.random.Random

@Composable
fun CurrenciesScreen( navHostController: NavHostController) {

    val currenciesViewModel: CurrenciesViewModel = hiltViewModel()
    val apiResponse by currenciesViewModel.currencyApiResponse.observeAsState(State.Loading)

    LaunchedEffect(true) {
        currenciesViewModel.fetchCurrencies()

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
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 10.dp,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(paddingValues = innerPadding)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    val coins = state.data
                    items(coins) { coin ->


                        val randomColors = Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1f
                        )
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(randomColors,
                                            Color.White.copy(alpha = 0.25f),
                                            Color.Transparent,randomColors)
                                    ),
                                    shape = MaterialTheme.shapes.medium
                                )
                                .wrapContentHeight()
                                .clip(MaterialTheme.shapes.medium),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = coin.displayIcon,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(horizontal = 18.dp, vertical = 10.dp)
                                    .height(94.dp),
                                colorFilter = ColorFilter.tint(Color.White)


                            )
                            Text(
                                text = coin.displayName,
                                fontFamily = sans,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xffedf0ef),
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(all = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}