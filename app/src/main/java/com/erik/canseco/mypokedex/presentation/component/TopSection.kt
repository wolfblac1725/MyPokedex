package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.erik.canseco.mypokedex.utility.Constant

@Composable
fun TopSection(name: String,number: Int, types:List<String>, dynamicColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = name,
                style = typography.headlineLarge,
                color = dynamicColor
            )

            Text(
                text = Constant.pokemonFormatNumber(number),
                style = typography.bodyLarge,
                color = dynamicColor
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Chips(details = types, dynamicColor = dynamicColor)
    }
}
