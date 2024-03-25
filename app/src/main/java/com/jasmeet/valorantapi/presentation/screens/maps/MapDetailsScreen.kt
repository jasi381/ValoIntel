package com.jasmeet.valorantapi.presentation.screens.maps

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.appcomponents.AnnotatedComposable
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.appcomponents.animatedBorder
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.appComponents.CustomAnimatedVisibility
import com.jasmeet.valorantapi.presentation.appComponents.ShowHideRow
import com.jasmeet.valorantapi.presentation.appComponents.TopAppBarComponent
import com.jasmeet.valorantapi.presentation.theme.sans
import com.jasmeet.valorantapi.presentation.utils.Utils
import com.jasmeet.valorantapi.presentation.viewModels.MapsViewModel
import kotlinx.coroutines.launch
import java.net.URLDecoder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapDetailsScreen(
    navHostController: NavHostController,
    mapUUID: String?
) {
    val decodeUUid = URLDecoder.decode(mapUUID, "UTF-8")
    val mapsViewModel: MapsViewModel = hiltViewModel()

    val mapData by mapsViewModel.mapDetails.observeAsState(State.Loading)

    var showDescription by remember { mutableStateOf(true) }
    var showMultiplier by remember { mutableStateOf(true) }
    var showScalar by remember { mutableStateOf(true) }
    var showCallOuts by remember { mutableStateOf(true) }


    LaunchedEffect(true) {
        mapsViewModel.fetchMapData(decodeUUid)
    }

    when (val state = mapData) {
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

            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = state.message.toString())

                }

            }
        }

        is State.Success -> {

            val imgList = listOfNotNull(
                state.data.data.splash,
                state.data.data.displayIcon,
                state.data.data.stylizedBackgroundImage ?: state.data.data.listViewIconTall,
            )

            val pagerState = rememberPagerState {
                imgList.size
            }

            val mapDetail = state.data.data

            val colors = listOf(
                Color(0xff978d83),
                Color(0xffd9d2c5),
                Color(0xffbfb5a9)
            )

            val pageCount = imgList.size

            val coroutineScope = rememberCoroutineScope()
            Scaffold(
                topBar = {
                    TopAppBarComponent(
                        title = mapDetail.displayName,
                        enableBackButton = true,
                        onBackClick = {
                            navHostController.popBackStack()
                        },
                    )
                }
            ) { innerPadding ->

                Column(
                    Modifier
                        .background(Color(0xff101118))
                        .fillMaxSize()
                        .padding(innerPadding)

                ) {

                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                LocalConfiguration.current.screenHeightDp.dp * 0.33f
                            )
                    ){
                        HorizontalPager(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                                .height(LocalConfiguration.current.screenHeightDp.dp * 0.3f),
                            state = pagerState,
                            userScrollEnabled = true
                        ) { page ->

                            AsyncImage(
                                model = imgList[page],
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .animatedBorder(
                                        borderColors = listOf(
                                            Color.White,
                                            Color.Green,

                                            ),
                                        backgroundColor = Color.White,
                                        borderWidth = 1.5.dp
                                    )
                                    .background(
                                        brush = Brush.verticalGradient(colors),
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .fillMaxSize()
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        Row(
                            Modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = (-20).dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pageCount) { iteration ->
                                val color =
                                    if (pagerState.currentPage == iteration) Color(0xffff4654) else Color(
                                        0xfff4f4f4
                                    )
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                        .clip(CircleShape)
                                        .size(10.dp)
                                        .background(color)


                                )
                            }
                        }

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            horizontalArrangement = if (pagerState.currentPage != 0)
                                Arrangement.SpaceBetween else Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            AnimatedVisibility(visible = pagerState.currentPage != 0) {
                                FilledTonalIconButton(
                                    onClick = {
                                        val previousPage = if (pagerState.currentPage > 0) pagerState.currentPage - 1 else 0
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(
                                                page = previousPage,
                                                animationSpec = tween(
                                                    durationMillis = 400,
                                                    delayMillis = 0,
                                                )
                                            )
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp),
                                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                                        containerColor = Color(0x4D000000)
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
                                        contentDescription = null
                                    )

                                }
                            }
                            AnimatedVisibility(visible = pagerState.currentPage != 2) {
                                FilledTonalIconButton(
                                    onClick = {

                                        val nextPage =
                                            if (pagerState.currentPage < 2) pagerState.currentPage + 1 else 2
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(
                                                page = nextPage,
                                                animationSpec = tween(
                                                    durationMillis = 400,
                                                    delayMillis = 0,
                                                )
                                            )
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp),
                                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                                        containerColor = Color(0x4D000000)
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                                        contentDescription = null
                                    )

                                }
                            }

                        }
                    }

                    Column(
                        Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .background(Color(0xff101118))
                            .padding(horizontal = 10.dp)

                            .navigationBarsPadding()
                    ) {

                        ShowHideRow(
                            onclick = { showDescription = !showDescription },
                            text = "Description",
                        )
                        CustomAnimatedVisibility(isVisible = showDescription) {
                            Text(
                                text = mapDetail.narrativeDescription
                                    ?: "No Description Available",
                                fontFamily = sans,
                                modifier = Modifier
                                    .padding(vertical = 16.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffedf0ef)
                            )
                        }

                        ShowHideRow(
                            onclick = { showMultiplier = !showMultiplier },
                            text = "Multipliers",
                        )
                        CustomAnimatedVisibility(isVisible = showMultiplier) {
                            Column(
                                modifier = Modifier
                            ) {

                                val xmulti =
                                    if (mapDetail.xMultiplier == 0.0) mapDetail.xMultiplier else Utils.formatScientificNotation(
                                        mapDetail.xMultiplier
                                    )
                                AnnotatedComposable(
                                    header = "x-Multiplier",
                                    content = xmulti.toString(),
                                    fontFamily = sans
                                )


                                val ymulti =
                                    if (mapDetail.yMultiplier == 0.0) mapDetail.yMultiplier else Utils.formatScientificNotation(
                                        mapDetail.yMultiplier
                                    )
                                AnnotatedComposable(
                                    header = "y-Multiplier",
                                    content = ymulti.toString(),
                                    fontFamily = sans
                                )
                            }

                        }

                        ShowHideRow(
                            onclick = { showScalar = !showScalar }, text = "ScalarToAdd",
                        )
                        CustomAnimatedVisibility(isVisible = showScalar) {
                            Column(
                                modifier = Modifier
                            ) {

                                Text(
                                    text = "X-ScalarToAdd : ${mapDetail.xScalarToAdd}",
                                    fontFamily = sans,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xffedf0ef)
                                )

                                Text(
                                    text = "Y-ScalarToAdd : ${mapDetail.yScalarToAdd}",
                                    fontFamily = sans,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xffedf0ef)
                                )
                            }

                        }

                        if (mapDetail.callouts != null) {
                            ShowHideRow(
                                onclick = { showCallOuts = !showCallOuts }, text = "CallOuts",
                            )
                            CustomAnimatedVisibility(isVisible = showCallOuts) {
                                Column {
                                    mapDetail.callouts.let {
                                        it.forEach { callout ->

                                            AnnotatedComposable(
                                                header = "Region Name",
                                                content = callout.regionName,
                                                modifier = Modifier.padding(top = 4.dp),
                                                fontFamily = sans
                                            )
                                            AnnotatedComposable(
                                                header = "Super-Region Name",
                                                content = callout.superRegionName,
                                                fontFamily = sans

                                            )
                                            AnnotatedComposable(
                                                header = "x-Location",
                                                content = callout.location.x.toString(),
                                                fontFamily = sans
                                            )
                                            AnnotatedComposable(
                                                header = "y-Location",
                                                content = callout.location.y.toString(),
                                                modifier = Modifier.padding(bottom = 4.dp),
                                                fontFamily = sans
                                            )

                                            HorizontalDivider()
                                        }
                                    }

                                }
                            }
                        }

                    }
                }
            }
        }
    }
}


