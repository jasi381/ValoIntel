package com.jasmeet.valorantapi.appComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jasmeet.valorantapi.ui.theme.valorantFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    title: String = "Valo Intel",
    enableBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    enableAction : Boolean = false,
    onActionClick : () -> Unit = {},
    actionIcon :ImageVector = Icons.Default.SortByAlpha
) {

    val isClicked = remember {
        mutableStateOf(false)
    }

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
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            if (enableAction) {
                Box(
                    modifier = Modifier.padding(end = 15.dp).size(32.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onActionClick.invoke()
                            isClicked.value = !isClicked.value

                        }.then(if(isClicked.value)Modifier.background(Color.White.copy(0.5f), CircleShape)else Modifier)
                ){

                    Icon(
                        imageVector = actionIcon,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center).padding(2.dp),
                        tint = if (isClicked.value)Color.White.copy(alpha = 0.7f) else Color.White
                    )

                }

            }
        }
    )

}



