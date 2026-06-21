package com.erik.canseco.mypokedex.presentation.screen.home

import com.erik.canseco.mypokedex.domain.model.PokemonModel

data class HomeDataState(
    val isLoading: Boolean = false,
    val pokemonList: List<PokemonModel> = emptyList(),
    val errorMessage: String? = null,
    val searchQuery: String = ""
)