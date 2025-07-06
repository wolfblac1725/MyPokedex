package com.erik.canseco.mypokedex.data.remote.entity

import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("chain") val chain: ChainLink
)
