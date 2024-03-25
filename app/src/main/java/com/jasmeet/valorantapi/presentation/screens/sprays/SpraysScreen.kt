package com.jasmeet.valorantapi.presentation.screens.sprays

import android.os.Build
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.appcomponents.animatedBorder
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.presentation.theme.valorantFont
import com.jasmeet.valorantapi.presentation.viewModels.SpraysViewModel

@Composable
fun SpraysScreen(navHostController: NavHostController) {


    val context = LocalContext.current

    val spraysViewModel: SpraysViewModel = hiltViewModel()
    val apiResponse by spraysViewModel.spraysApiResponse.observeAsState(State.Loading)

    var sortAscending by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(true) {
        spraysViewModel.fetchSprays()
    }


    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Sprays",
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

            is State.Success -> {

                val filteredList = state.data.filter {
                    it.animationGif != null
                }

                val sortedData = if (sortAscending) {
                    filteredList
                } else {
                    filteredList.sortedBy { it.displayName }
                }


                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                        .background(Color(0xff101118))
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp)
                        .padding(innerPadding),
                    horizontalArrangement = Arrangement.spacedBy(25.dp),
                    verticalItemSpacing = 25.dp

                ) {
                    items(sortedData){ spray->

                        Column {
                            Surface(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .animatedBorder(
                                        borderColors = listOf(
                                            Color(0xffffffff),
                                            Color(0xffff4654),


                                            ),
                                        backgroundColor = Color.White,
                                        borderWidth = 1.dp
                                    )
                                    .size(180.dp),
                                shape = MaterialTheme.shapes.large,
                                color = Color.Transparent
                            ) {
                                Column(
                                    Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    val imageLoader = ImageLoader.Builder(context).components {
                                        if (Build.VERSION.SDK_INT >= 28) {
                                            add(ImageDecoderDecoder.Factory())
                                        } else {
                                            add(GifDecoder.Factory())
                                        }
                                    }.build()

                                    val painter = rememberAsyncImagePainter(
                                        model = spray.animationGif,
                                        imageLoader = imageLoader,
                                    )

                                    Image(
                                        painter = painter,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .clip(MaterialTheme.shapes.medium)
                                            .background(Color.Black.copy(0.95f))
                                            .fillMaxSize()
                                    )
                                }
                            }

                            Text(
                                text = spray.displayName,
                                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp),
                                fontFamily = valorantFont,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffedf0ef),
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                }

            }
        }
    }
}