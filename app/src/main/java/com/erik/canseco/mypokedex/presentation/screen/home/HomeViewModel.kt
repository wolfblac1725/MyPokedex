package com.erik.canseco.mypokedex.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    var state by mutableStateOf(HomeDataState())
    private set

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val pokemonList: Flow<PagingData<PokemonModel>> = _searchQuery
        .flatMapLatest { query ->
            pokemonRepository.getPokemonList()
                .map { pagingData ->
                    pagingData.filter { pokemon ->
                        pokemon.name.contains(query, ignoreCase = true)
                    }
                }
        }
        .cachedIn(viewModelScope)

    fun onSearchQueryChange(newQuery: String) {
        state = state.copy(searchQuery = newQuery)
        _searchQuery.value = newQuery
    }
}