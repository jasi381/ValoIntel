package com.jasmeet.valorantapi.appComponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.jasmeet.valorantapi.presentation.theme.sans

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text : String,
    fontFamily: FontFamily = sans,
    fontSize:TextUnit = 22.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    color: Color = Color(0xffedf0ef)
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier
    )

}