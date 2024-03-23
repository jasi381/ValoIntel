package com.jasmeet.valorantapi.appComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jasmeet.valorantapi.presentation.theme.sans

@Composable
fun ShowHideRow(
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    text : String,
    subtitle : String? = null
) {

    val isExpanded = remember { mutableStateOf(true) }


    Row(
        modifier
            .fillMaxWidth()
            .customClickable {
                onclick.invoke()
                isExpanded.value = !isExpanded.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
       Row( verticalAlignment = Alignment.CenterVertically,) {
            Text(
                text = text,
                fontFamily = sans,
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xffedf0ef)
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontFamily = sans,
                    modifier = Modifier.padding(start = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0x80EDF0EF)
                )
            }
        }

        FilledTonalIconButton(
            onClick = {
                onclick.invoke()
                isExpanded.value = !isExpanded.value
            },
            modifier = modifier.size(24.dp)
        ) {
            Icon(
                imageVector = if (isExpanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = null
            )
        }

    }


}


