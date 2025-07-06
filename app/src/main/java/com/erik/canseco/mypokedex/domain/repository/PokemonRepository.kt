package com.erik.canseco.mypokedex.domain.repository

import androidx.paging.PagingData
import com.erik.canseco.mypokedex.domain.model.PokemonDetailModel
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.utility.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonModel>>
    fun getPokemonInfo(id: String):Flow<Resource<PokemonDetailModel>>
}