package com.erik.canseco.mypokedex.data.remote.entity

import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.utility.Constant

data class Pokemon(
    val name: String,
    val url: String
) {
    fun toPokemon(): PokemonModel = PokemonModel(
       name = name,
       url = url,
       imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${Constant.pokemonUrlToId(url)}.png"
    )

}