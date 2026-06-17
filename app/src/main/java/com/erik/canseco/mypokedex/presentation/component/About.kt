package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erik.canseco.mypokedex.domain.model.PokemonDetailModel


@Composable
fun About(
    state: PokemonDetailModel,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ){
        item {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Altura: ",
                        fontSize = 20.sp,

                        )
                    Text(
                        text = state.height.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Peso: ",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = state.weight.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        item {
            Text(
                text = "Habilidades: ",
                fontSize = 20.sp,
                modifier = modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
        item {
            Chips(details = state.abilities)
        }

        items(state.flavorText.size) { index ->
            Text(
                text = state.flavorText[index].trim().replace("\n"," "),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(16.dp)
            )

        }
    }
}