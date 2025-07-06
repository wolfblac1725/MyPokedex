package com.erik.canseco.mypokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.erik.canseco.mypokedex.data.remote.PokemonApi
import com.erik.canseco.mypokedex.data.remote.PokemonPagingSource
import com.erik.canseco.mypokedex.domain.model.PokemonDetailModel
import com.erik.canseco.mypokedex.domain.model.PokemonEvolutionModel
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.domain.model.StatModel
import com.erik.canseco.mypokedex.domain.repository.PokemonRepository
import com.erik.canseco.mypokedex.utility.Constant
import com.erik.canseco.mypokedex.utility.Constant.pokemonNextItem
import com.erik.canseco.mypokedex.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(
    private val pokemonApi: PokemonApi,
): PokemonRepository {
    override fun getPokemonList(): Flow<PagingData<PokemonModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonApi)
            }
        ).flow
    }

    override fun getPokemonInfo(id: String): Flow<Resource<PokemonDetailModel>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val pokemonResult = pokemonApi.getPokemonInfo(id)

                val abilitiesArray = mutableListOf<String>()
                for (ability in pokemonResult.abilities) {
                    val abilities = pokemonApi.getPokemonAbility(ability.ability.url)
                    for (abilityName in abilities.names) {
                        if (abilityName.language.name == "es") {
                            abilitiesArray.add(abilityName.name)
                        }
                    }
                }
                val pokemonStatArray = mutableListOf<StatModel>()
                for (stat in pokemonResult.stats) {
                    val pokemonStat = pokemonApi.getPokemonStat(stat.stat.url)
                    for (statName in pokemonStat.names) {
                        if (statName.language.name == "es") {
                            pokemonStatArray.add(StatModel(statName.name,stat.baseStat))
                        }
                    }
                }
                val pokemonTypeArray = mutableListOf<String>()
                for (type in pokemonResult.types) {
                    val pokemonType = pokemonApi.getPokemonType(type.type.url)
                    for (typeName in pokemonType.names) {
                        if (typeName.language.name == "es") {
                            pokemonTypeArray.add(typeName.name)
                        }
                    }
                }
                val pokemonSpecies = pokemonApi.getPokemonSpecies(pokemonResult.species.url)
                val pokemonFlavorString = mutableListOf<String>()
                for (flavorText in pokemonSpecies.flavorTextEntries) {
                    if (flavorText.language.name == "es") {
                        pokemonFlavorString.add(flavorText.flavorText)
                    }
                }

                val pokemonEvolutionChain = pokemonApi.getEvolutionChain(pokemonSpecies.evolutionChain.url)
                val listPokemonEvolution= mutableListOf<PokemonEvolutionModel>()
                pokemonNextItem(pokemonEvolutionChain.chain,listPokemonEvolution)

                val pokemonDetail = PokemonDetailModel(
                    id = pokemonResult.id,
                    name = pokemonResult.name,
                    imageUrl = Constant.pokemonUrlImage(pokemonResult.id),
                    height = pokemonResult.height,
                    weight = pokemonResult.weight,
                    abilities = abilitiesArray,
                    stats = pokemonStatArray,
                    types = pokemonTypeArray,
                    flavorText = pokemonFlavorString,
                    evolutionChain = listPokemonEvolution
                )
                emit(Resource.Success(pokemonDetail))
            }catch (_: Exception) {
                emit(Resource.Error("Error al obtener la informacion del pokemon"))
            }finally {
                emit(Resource.Loading(false))

            }

        }
    }
}