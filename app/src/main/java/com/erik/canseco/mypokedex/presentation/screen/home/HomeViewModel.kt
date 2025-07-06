package com.erik.canseco.mypokedex.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
): ViewModel() {
    var state by mutableStateOf(HomeDataState())
    private set
    val pokemonList: Flow<PagingData<PokemonModel>> = pokemonRepository.getPokemonList()
            .cachedIn(viewModelScope)


}