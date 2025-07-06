package com.erik.canseco.mypokedex.presentation.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erik.canseco.mypokedex.domain.repository.PokemonRepository
import com.erik.canseco.mypokedex.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    var state by mutableStateOf(DetailDataState())

    fun getPokemonInfo(id: String) {
        viewModelScope.launch {
            val  result = pokemonRepository.getPokemonInfo(id)
            result.collect{
                when(it){
                    is Resource.Success -> {
                        state = state.copy(
                            pokemonInfo = it.data,
                            isLoading = false,
                            message = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            pokemonInfo = null,
                            isLoading = false,
                            message = it.message
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = it.isLoading
                        )
                    }
                }
            }
        }
    }
}