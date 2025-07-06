package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.erik.canseco.mypokedex.domain.model.PokemonDetailModel
import com.erik.canseco.mypokedex.utility.Constant

@Composable
fun InfoCard(
    state: PokemonDetailModel,
    color: Color,
    modifier: Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.70f),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 20.dp),
        ) {
            var selectionTab by remember {
                mutableStateOf(0)
            }

            val tabs = Constant.getTabs()
            TabRow(
                selectedTabIndex = selectionTab,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectionTab])
                            .height(4.dp)
                            .background(color = color, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )
                },
                containerColor = Color.White
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectionTab == index,
                        onClick = { selectionTab = index },
                        text = { Text(title) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Sections(tabs[selectionTab], state,color)
        }

    }
}