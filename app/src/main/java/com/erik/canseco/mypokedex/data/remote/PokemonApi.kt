package com.erik.canseco.mypokedex.data.remote

import com.erik.canseco.mypokedex.data.remote.entity.EvolutionChainResponse
import com.erik.canseco.mypokedex.data.remote.entity.PokemonHeader
import com.erik.canseco.mypokedex.data.remote.entity.PokemonInfo
import com.erik.canseco.mypokedex.data.remote.entity.PokemonAbility
import com.erik.canseco.mypokedex.data.remote.entity.PokemonSpecies
import com.erik.canseco.mypokedex.data.remote.entity.PokemonStat
import com.erik.canseco.mypokedex.data.remote.entity.PokemonType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonHeader

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonInfo(
        @Path("pokemonId") name: String
    ): PokemonInfo

    @GET
    suspend fun getPokemonAbility(
        @Url url: String
    ): PokemonAbility

    @GET
    suspend fun getPokemonStat(
        @Url url: String
    ): PokemonStat
    @GET
    suspend fun getPokemonType(
        @Url url: String
    ): PokemonType

    @GET
    suspend fun getPokemonSpecies(
        @Url url: String
    ): PokemonSpecies

    @GET
    suspend fun getEvolutionChain(
        @Url url: String
    ): EvolutionChainResponse



}