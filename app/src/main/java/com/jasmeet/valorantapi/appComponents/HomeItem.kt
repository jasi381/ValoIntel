package com.jasmeet.valorantapi.appComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jasmeet.valorantapi.ui.theme.valorantFont

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    imageUrl: String ,
    title: String,
    onItemClick : () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onItemClick.invoke()
            }
            .animatedBorder(
                borderColors = listOf(
                    Color(0xffc7f458),
                    Color(0xff3a7233)
                ),
                backgroundColor = Color.White,
            )
            .fillMaxWidth(),
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,

            )

        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 5.dp),
            fontFamily = valorantFont,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}