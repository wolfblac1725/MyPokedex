package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.erik.canseco.mypokedex.R
import com.erik.canseco.mypokedex.domain.model.PokemonEvolutionModel

@Composable
fun Evolution(
    evolution: List<PokemonEvolutionModel>,
    color : Color,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ){ 
        items(evolution.size) { index ->
            ItemPokemonEvolution(pokemon = evolution[index],color, index != evolution.size-1)
        }
    }
}

@Composable
fun ItemPokemonEvolution(
    pokemon: PokemonEvolutionModel,
    color: Color,
    isShowArrow: Boolean,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    Column(
        modifier=modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = pokemon.name,
            modifier = Modifier
                .padding(8.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(pokemon.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(200.dp)
        )
        if(isShowArrow)
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(color),
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Arrow Down",
            )
    }
}