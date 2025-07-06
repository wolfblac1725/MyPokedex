package com.erik.canseco.mypokedex.utility

import com.erik.canseco.mypokedex.data.remote.entity.ChainLink
import com.erik.canseco.mypokedex.domain.model.PokemonEvolutionModel
import java.util.Locale

object Constant {
    const val PAGE_SIZE = 20
    enum class TabsTitle(val title: String) {
        BASE_STATS("Estadisticas"),
        ABOUT("Acerca de"),
        EVOLUTION("Evolución"),
    }

    fun pokemonUrlToId(url: String): String {
        return url.split("/")[6]
    }
    fun pokemonUrlImage(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }
    fun pokemonNextItem( chain: ChainLink, pokemonEvolutionChain: MutableList<PokemonEvolutionModel>){
        val species = chain.species
        pokemonEvolutionChain.add(
            PokemonEvolutionModel(
                pokemonUrlToId(species.url).toInt(),
                species.name,
                pokemonUrlImage(pokemonUrlToId(species.url).toInt())
            )
        )
        val evolvesToArray = chain.evolvesTo
        for (i in 0 until evolvesToArray.size) {
            val nextPokemon = evolvesToArray[i]
            if (nextPokemon != null) {
                pokemonNextItem(nextPokemon, pokemonEvolutionChain)
            }
        }
    }
    fun pokemonFormatNumber(number: Int): String {
        return String.format(Locale.getDefault(),"#%03d", number)
    }
    fun getTabs():List<String>{
        val list = mutableListOf<String>()
        for (i in TabsTitle.entries) {
            list.add(i.title)
        }
        return list
    }
}