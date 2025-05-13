package com.erik.canseco.mypokedex.data.remote.entity

data class PokemonHeader(
    val count: Int,
    val next: String,
    val previous: String,
    val pokemon: List<Pokemon>
)