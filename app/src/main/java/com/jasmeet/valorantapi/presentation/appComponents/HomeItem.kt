package com.jasmeet.valorantapi.presentation.appComponents

import android.graphics.Insets.add
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.jasmeet.appcomponents.animatedBorder
import com.jasmeet.valorantapi.presentation.theme.valorantFont

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    imageUrl: String ,
    title: String,
    onItemClick : () -> Unit,
    enableGifSupport : Boolean = false,
    contentScale: ContentScale = ContentScale.FillBounds
) {

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context).components {
        if (SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()

    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        imageLoader = imageLoader,
    )

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

        if (!enableGifSupport){
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxSize(),
                contentScale = contentScale,

                )
        }else{
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xff22fcef),
                            Color(0xff212226),
                            Color(0xffb5a794),

                        )
                    ))
                    .fillMaxSize().scale(1.5f, 1.58f),
            )
        }

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