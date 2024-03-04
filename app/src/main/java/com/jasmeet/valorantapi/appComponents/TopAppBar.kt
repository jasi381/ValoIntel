package com.jasmeet.valorantapi.appComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.jasmeet.valorantapi.ui.theme.valorantFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    title: String = "Valo Intel",
    enableBackButton: Boolean = false,
    onBackClick: () -> Unit = {}
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xff0b1514)
        ),

        title = {
            Text(
                text = title,
                fontFamily = valorantFont,
                fontSize = 22.sp,
                color = Color(0xffff4654)
            )
        },
        navigationIcon = {
            if (enableBackButton) {
                IconButton(
                    onClick = {
                        onBackClick.invoke()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
    )

}