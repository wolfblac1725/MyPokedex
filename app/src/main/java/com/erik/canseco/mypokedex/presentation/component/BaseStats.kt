package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.erik.canseco.mypokedex.domain.model.StatModel


@Composable
fun BaseStats(state:List<StatModel>,color: Color, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(state.size) { index ->
            ItemState(stat = state[index],color)
        }
    }
}