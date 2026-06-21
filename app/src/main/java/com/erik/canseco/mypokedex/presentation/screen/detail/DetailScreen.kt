package com.erik.canseco.mypokedex.presentation.screen.detail

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.erik.canseco.mypokedex.R
import com.erik.canseco.mypokedex.presentation.component.ErrorItem
import com.erik.canseco.mypokedex.presentation.component.InfoCard
import com.erik.canseco.mypokedex.presentation.component.Loading
import com.erik.canseco.mypokedex.presentation.component.TopSection
import com.erik.canseco.mypokedex.ui.theme.Carbon
import com.erik.canseco.mypokedex.ui.theme.Purple80
import com.erik.canseco.mypokedex.utility.Constant


@Composable
fun DetailScreenRoot(
    modifier: Modifier = Modifier,
    id: String? = null,
    name: String? = null,
    color: Int? = null,
    onBackClick: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        id?.let {
            detailViewModel.getPokemonInfo(it)
        }
    }
    val state = detailViewModel.state
    DetailScreen(
        modifier = modifier,
        state = state,
        name = name?: "",
        color = color,
        id = id,
        onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailDataState,
    name: String,
    color: Int?,
    id: String? = null,
    onBackClick: () -> Unit,

) {
    var dominantColor by remember {
        mutableStateOf(Color((color?: Purple80) as Int))
    }
    val context = LocalContext.current
    val isDarkBackground = remember(dominantColor) {
        val luminance = (0.299 * dominantColor.red + 0.587 * dominantColor.green + 0.114 * dominantColor.blue)
        luminance < 0.5
    }
    val dynamicColor = if (isDarkBackground) Color.White else Carbon
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        color = Carbon
                    )
                },
                modifier = Modifier.padding(bottom = 8.dp),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = dynamicColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = dominantColor
                )
            )
        },
        content = { padding ->
           if(state.isLoading){
               Loading(Modifier.padding(padding))
           }else if(state.message != null)
               ErrorItem(message = state.message,Modifier.padding(padding), onClickRetry = {})
            else
               Box(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(dominantColor)
                       .padding(padding)
               ){
                   state.pokemonInfo?.let {
                       TopSection(it.name,it.id,it.types,dynamicColor)
                       InfoCard(state = it ,color = dominantColor , modifier = Modifier.align(Alignment.BottomCenter))
                       id?.let { id ->
                           AsyncImage(
                               model = ImageRequest.Builder(context)
                                   .data(Constant.pokemonUrlImage(id = id.toInt()))
                                   .crossfade(true)
                                   .build(),
                               contentDescription = name,
                               modifier = Modifier
                                   .size(200.dp)
                                   .align(Alignment.TopCenter)
                                   .offset(y = 80.dp)
                           )
                       }
                   }
               }

        }
    )
}

