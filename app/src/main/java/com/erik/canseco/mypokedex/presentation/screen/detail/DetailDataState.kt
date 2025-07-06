package com.erik.canseco.mypokedex.presentation.screen.detail

import com.erik.canseco.mypokedex.domain.model.PokemonDetailModel

data class DetailDataState(
    val isLoading:Boolean = false,
    val message: String? = null,
    val pokemonInfo: PokemonDetailModel? = null
)