package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TypeChip(type: String, dynamicColor: Color) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.3f),
    ) {
        Text(
            text = type,
            color = dynamicColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = typography.bodyMedium
        )
    }
}