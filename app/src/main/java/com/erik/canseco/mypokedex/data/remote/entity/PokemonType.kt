package com.erik.canseco.mypokedex.data.remote.entity

data class PokemonType(
    val name: String,
    val names: List<Names>,
    val pokemon: List<PokemonSameType>

)
