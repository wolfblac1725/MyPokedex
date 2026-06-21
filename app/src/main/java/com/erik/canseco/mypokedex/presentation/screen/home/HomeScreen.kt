package com.erik.canseco.mypokedex.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.erik.canseco.mypokedex.R
import com.erik.canseco.mypokedex.domain.model.PokemonModel
import com.erik.canseco.mypokedex.presentation.component.PokemonCard
import com.erik.canseco.mypokedex.presentation.component.ErrorItem
import com.erik.canseco.mypokedex.presentation.component.Loading
import com.erik.canseco.mypokedex.utility.Constant.pokemonUrlToId

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    onItemClick: (id: String,name: String,color: Int) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        pokemonList = homeViewModel.pokemonList.collectAsLazyPagingItems(),
        searchQuery = homeViewModel.state.searchQuery,
        onSearchQueryChange = homeViewModel::onSearchQueryChange,
        modifier = modifier,
        onItemClick = onItemClick
    )
    
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    pokemonList: LazyPagingItems<PokemonModel>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onItemClick: (id: String,name: String,color: Int) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Scaffold (
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        inputField ={
                            SearchBarDefaults.InputField(
                                query = searchQuery,
                                onQueryChange = onSearchQueryChange,
                                onSearch = {},
                                expanded = expanded,
                                onExpandedChange = { expanded = it },
                                placeholder = { Text(stringResource(R.string.app_name)) }
                            )
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    ) {}
                },
                modifier = Modifier.padding(8.dp)
            )
        },
        content = { padding ->

            LazyVerticalGrid (
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    count = pokemonList.itemCount,
                    key = pokemonList.itemKey { pokemon -> pokemon.name }
                ) { index ->
                    val pokemon = pokemonList[index]
                    if (pokemon != null) {
                        PokemonCard(
                            pokemon = pokemon,
                            onClick = { color ->
                                onItemClick(pokemonUrlToId(pokemon.url),pokemon.name, color)
                            }
                        )
                    }

                }
            }
            pokemonList.apply {
                when{
                    loadState.refresh is LoadState.Loading -> {
                         Loading()
                    }
                    loadState.append is LoadState.Loading -> {
                        Loading()
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = pokemonList.loadState.refresh as LoadState.Error

                        ErrorItem(
                            message = "Error de carga: ${e.error.localizedMessage}",
                            modifier = Modifier.fillMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                    loadState.append is LoadState.Error -> {
                        val e = pokemonList.loadState.append as LoadState.Error
                            ErrorItem(
                                message = stringResource(
                                    R.string.error_al_cargar_m_s,
                                    e.error.localizedMessage
                                ),
                                onClickRetry = { retry() }
                            )
                    }
                }
            }
        }
    )
}
