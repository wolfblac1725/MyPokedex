package com.erik.canseco.mypokedex.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil3.network.HttpException
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.utility.Constant
import java.io.IOException

class PokemonPagingSource(
    private val pokemonApi: PokemonApi
):PagingSource<Int,PokemonModel>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
       return state.anchorPosition?.let { anchorPosition ->
           state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
           state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,PokemonModel> {
        val position = params.key ?: 0
        val limit = Constant.PAGE_SIZE
        return try {
            val response = pokemonApi.getPokemonList(limit, position * limit)
            val pokemonList = response.results.map { it.toPokemon() }
            LoadResult.Page(
                data = pokemonList,
                prevKey = if (position == 0) null else position - 1 ,
                nextKey = if (pokemonList.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}