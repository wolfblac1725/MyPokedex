package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.erik.canseco.mypokedex.domain.model.PokemonDetailModel
import com.erik.canseco.mypokedex.utility.Constant

@Composable
fun Sections(selectionTab: String, state: PokemonDetailModel,color: Color, modifier: Modifier = Modifier) {
    when(selectionTab){
        Constant.TabsTitle.BASE_STATS.title -> BaseStats(state = state.stats,color,modifier)
        Constant.TabsTitle.ABOUT.title -> About(state = state,modifier)
        Constant.TabsTitle.EVOLUTION.title -> Evolution(evolution =state.evolutionChain,color,modifier)
    }

}