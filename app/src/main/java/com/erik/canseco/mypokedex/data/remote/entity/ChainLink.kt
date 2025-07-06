package com.erik.canseco.mypokedex.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ChainLink (
    @SerializedName("species") val species: Detail,
    @SerializedName("evolves_to") val evolvesTo: List<ChainLink?>
)