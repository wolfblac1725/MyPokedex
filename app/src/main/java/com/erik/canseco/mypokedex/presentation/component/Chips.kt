package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.erik.canseco.mypokedex.ui.theme.Carbon

@Composable
fun Chips( modifier: Modifier = Modifier,details:List<String>, dynamicColor: Color = Carbon) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (detail in details) {
            TypeChip(detail, dynamicColor)
        }
    }

}