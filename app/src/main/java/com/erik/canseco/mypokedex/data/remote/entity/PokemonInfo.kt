package com.erik.canseco.mypokedex.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PokemonInfo(
    val id: Int,
    val name: String,
    val abilities: List<Abilitie>,
    @SerializedName("base_experience")
    val baseExperience:Int,
    val height: Int,
    val weight: Int,
    val species: Detail,
    val stats: List<Stat>,
    val types: List<Type>
){

}
