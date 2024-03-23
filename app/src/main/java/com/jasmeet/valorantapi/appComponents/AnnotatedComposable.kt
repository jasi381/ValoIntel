package com.jasmeet.valorantapi.appComponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.jasmeet.valorantapi.presentation.theme.sans

@Composable
fun AnnotatedComposable(
    header:String,
    content:String,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                fontFamily = sans
            )
        ) {
            append("$header : ")
        }
        withStyle(
            style = SpanStyle(
                color =  Color(0xffADD8E6),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                fontFamily = sans
            )
        ) {
            append(content)
        }
    }

    Text(
        text = annotatedString ,
        fontFamily = sans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}
