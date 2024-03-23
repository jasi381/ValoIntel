package com.jasmeet.valorantapi.presentation.appComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun CustomAnimatedVisibility(
    isVisible: Boolean,
    composable: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
    ) {
        composable()

    }
}