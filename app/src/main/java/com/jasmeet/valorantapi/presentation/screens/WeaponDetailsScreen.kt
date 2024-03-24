package com.jasmeet.valorantapi.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.appComponents.CustomAnimatedVisibility
import com.jasmeet.valorantapi.presentation.appComponents.ShowHideRow
import com.jasmeet.valorantapi.presentation.theme.sans
import com.jasmeet.valorantapi.presentation.utils.Utils
import com.jasmeet.valorantapi.presentation.viewModels.WeaponsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLDecoder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeaponDetailsScreen(navHostController: NavHostController, weaponUUID: String?) {

    val decodedUUID = URLDecoder.decode(weaponUUID, "UTF-8")
    val weaponsViewModel: WeaponsViewModel = hiltViewModel()
    val weaponsDetails by weaponsViewModel.weaponDetails.observeAsState(State.Loading)

    var imageUrl by remember { mutableStateOf<String?>(null) }

    var isSkinsExpanded by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = Unit) {
        weaponsViewModel.fetchWeaponsData(decodedUUID.trim())
    }

    Scaffold(
        topBar = {
        },
    ) {

        when (val state = weaponsDetails) {
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
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = state.message.toString())

                    }

                }
            }

            is State.Success -> {

                val weaponsData = state.data.data

                var dominantColors by remember { mutableStateOf(Color.White to Color.White) }

                LaunchedEffect(key1 = imageUrl) {
                    val colors = withContext(Dispatchers.IO) {
                        Utils.getDominantColorsFromUrl(imageUrl?:weaponsData.skins.first().chromas[0].fullRender)
                    }
                    dominantColors = colors
                }

                Column {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenHeightDp.dp * 0.33f)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        dominantColors.first,
                                        Color.White.copy(alpha = 0.3f),
                                        dominantColors.second
                                    )
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
                                model = imageUrl
                                    ?: weaponsData.skins.first().chromas.first().fullRender,
                                contentDescription = "Header",
                                modifier = Modifier
                                    .padding(vertical = 15.dp, horizontal = 25.dp)
                                    .align(Alignment.Center)
                                    .fillMaxSize(),

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


                        Row(
                            Modifier
                                .padding(vertical = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = weaponsData.displayName,
                                fontFamily = sans,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xffedf0ef)
                            )
                            Text(
                                text = Utils.getCategoryName(weaponsData.category),
                                fontFamily = sans,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffedf0ef)
                            )
                        }

                        ShowHideRow(onclick = { isSkinsExpanded = !isSkinsExpanded }, text = "Skins")

                        CustomAnimatedVisibility(
                            isVisible = isSkinsExpanded,
                        ) {
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Fixed(2),
                                verticalItemSpacing = 10.dp,
                                modifier = Modifier
                                    .padding(bottom = 16.dp)
                                    .fillMaxWidth()
                                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.8f),
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                val skins =
                                    weaponsData.skins.filter { !it.displayIcon.isNullOrEmpty() }
                                        .filter {
                                            !it.displayName.contains("Random") && !it.displayName.contains(
                                                "Standard"
                                            )
                                        }



                                items(skins) { skin ->

                                    var dominantColors2 by remember(skin.displayIcon) { mutableStateOf(Color.White to Color.White)  }

                                    LaunchedEffect(key1 = skin.displayIcon) {
                                        val color = withContext(Dispatchers.IO) {
                                            Utils.getDominantColorsFromUrl(skin.displayIcon)
                                        }
                                        dominantColors2 = color
                                    }
                                    Column(
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                            .fillMaxWidth()
                                            .background(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(dominantColors2.first, dominantColors2.second),
                                                ),
                                                shape = MaterialTheme.shapes.medium
                                            )
                                            .wrapContentHeight()
                                            .clip(MaterialTheme.shapes.medium)
                                            .clickable {
                                                imageUrl = skin.displayIcon
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = skin.displayIcon,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(horizontal = 18.dp)
                                                .height(94.dp)
                                                .scale(1.1f)


                                        )
                                        Text(
                                            text = skin.displayName,
                                            fontFamily = sans,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xffedf0ef),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(
                                                bottom = 5.dp,
                                                start = 4.dp,
                                                end = 4.dp
                                            )
                                        )
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

