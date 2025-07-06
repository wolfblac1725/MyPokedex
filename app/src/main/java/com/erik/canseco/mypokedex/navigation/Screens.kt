package com.erik.canseco.mypokedex.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(
    val id: String? = null,
    val name: String? = null,
    val color: Int? = null
)