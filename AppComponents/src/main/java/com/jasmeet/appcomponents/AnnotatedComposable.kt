package com.jasmeet.appcomponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun AnnotatedComposable(
    header:String,
    content:String,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                fontFamily = fontFamily
            )
        ) {
            append("$header : ")
        }
        withStyle(
            style = SpanStyle(
                color =  Color(0xffADD8E6),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                fontFamily = fontFamily
            )
        ) {
            append(content)
        }
    }

    Text(
        text = annotatedString ,
        fontFamily = fontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}
