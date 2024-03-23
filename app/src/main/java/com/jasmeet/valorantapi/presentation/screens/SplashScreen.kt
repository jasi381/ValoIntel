package com.jasmeet.valorantapi.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.jasmeet.appcomponents.LoaderComponent
import com.jasmeet.valorantapi.R
import com.jasmeet.valorantapi.presentation.theme.valorantFont

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val scale = remember {
        Animatable(initialValue = 0.2f)
    }

    val alpha = remember {
        Animatable(initialValue = 0.1f)
    }


    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 1.15f,
                animationSpec = tween(
                    durationMillis = 1700,
                    easing = {
                        OvershootInterpolator(1.25f).getInterpolation(it)
                    }
                )
            )
            alpha.animateTo(
                targetValue = 1.15f,
                animationSpec = tween(
                    durationMillis = 1700,
                    easing = {
                        OvershootInterpolator(1.25f).getInterpolation(it)
                    }
                )
            )
            val navOptions = NavOptions.Builder()
                .setPopUpTo(Screens.SplashScreen.route, inclusive = true)
                .build()

            navHostController.navigate(Screens.HomeScreen.route, navOptions)
        }
    )


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff0F1822))
    ) {
        Column(
            Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoImage(scale)
            Spacer(Modifier.height(15.dp))
            TitleText(alpha)

        }
        LoaderComponent(
            modifier = Modifier
                .height(120.dp)
                .width(180.dp)
                .navigationBarsPadding()
                .align(Alignment.BottomCenter),
            rawRes = R.raw.loader

        )
    }
}

@Composable
private fun TitleText(alpha: Animatable<Float, AnimationVector1D>) {
    Text(
        text = stringResource(id = R.string.app_name),
        color = Color(0xffFF4654),
        fontSize = 28.sp,
        fontFamily = valorantFont,
        modifier = Modifier.alpha(alpha.value)
    )
}

@Composable
private fun LogoImage(scale: Animatable<Float, AnimationVector1D>) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.logo),
        contentDescription = null,
        colorFilter = ColorFilter.tint(Color(0xffFF4654)),
        modifier = Modifier
            .size(width = 150.dp, height = 120.dp)
            .scale(scale.value)

    )
}

