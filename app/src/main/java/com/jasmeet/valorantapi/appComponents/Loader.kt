package com.jasmeet.valorantapi.appComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jasmeet.valorantapi.R

@Composable
fun LoaderComponent(rawRes :Int = R.raw.loader,modifier: Modifier) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(rawRes)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 2f,
        restartOnPlay = false

    )

    @Suppress("DEPRECATION")
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier,
//        contentScale = ContentScale.Crop,
        renderMode = RenderMode.HARDWARE

    )
}


