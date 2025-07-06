package com.erik.canseco.mypokedex.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("evolution_chain") val evolutionChain : EvolutionChain,
    @SerializedName("flavor_text_entries") val flavorTextEntries : List<FlavorTextEntries>
)
