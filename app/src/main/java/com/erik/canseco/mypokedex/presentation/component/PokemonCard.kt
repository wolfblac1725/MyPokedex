package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.toBitmap
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.ui.theme.Carbon
import com.erik.canseco.mypokedex.ui.theme.PurpleGrey80

@Composable
fun  PokemonCard(
    pokemon: PokemonModel,
    onClick: (color: Int) -> Unit = {}
) {
    val context = LocalContext.current
    var dominantColor by remember {
        mutableStateOf(PurpleGrey80)
    }
    val isDarkBackground = remember(dominantColor) {
        val luminance = (0.299 * dominantColor.red + 0.587 * dominantColor.green + 0.114 * dominantColor.blue)
        luminance < 0.5
    }
    val textColor = if (isDarkBackground) Color.White else Carbon
    LaunchedEffect(pokemon.imageUrl) {
        val imageLoader = ImageLoader(context)

        val request = ImageRequest.Builder(context)
            .data(pokemon.imageUrl)
            .allowHardware(false)
            .build()
        val result = imageLoader.execute(request)
        val image = result.image
        val bitmap = image?.toBitmap()
        bitmap?.let {
            Palette.from(it).generate { palette ->
                val colorDeFondo = palette?.getDominantColor(dominantColor.toArgb())
                colorDeFondo?.let {
                    dominantColor = Color(it)
                }
            }
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = dominantColor
        )
    ){
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onClick(dominantColor.toArgb())
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(pokemon.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = pokemon.name,
                modifier = Modifier.height(170.dp)
            )
            Text(
                text = pokemon.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                ),

                )
        }
    }

}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokemonCard(
        pokemon = PokemonModel(
        "Pikachu",
        "https://pokeapi.co/api/v2/pokemon/25/",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
        )
    )
}
