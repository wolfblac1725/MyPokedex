package com.erik.canseco.mypokedex.domain.model

data class PokemonDetailModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val abilities: List<String>,
    val stats: List<StatModel>,
    val types: List<String>,
    val flavorText: List<String>,
    val evolutionChain: List<PokemonEvolutionModel>
)
