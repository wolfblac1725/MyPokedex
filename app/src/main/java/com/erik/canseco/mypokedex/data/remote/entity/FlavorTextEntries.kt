package com.erik.canseco.mypokedex.data.remote.entity

import com.google.gson.annotations.SerializedName

data class FlavorTextEntries(
    @SerializedName("flavor_text") val flavorText: String,
    @SerializedName("language") val language: Detail,
    @SerializedName("version") val version: Detail
)
