package com.erik.canseco.mypokedex.presentation.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erik.canseco.mypokedex.domain.model.StatModel
import com.erik.canseco.mypokedex.ui.theme.MyPokedexTheme
import kotlinx.coroutines.delay

@Composable
fun ItemState(
    stat: StatModel,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        var progress by remember { mutableFloatStateOf(0f) }
        val animatedProgress by
        animateFloatAsState(
            targetValue = progress.coerceIn(0f,1f),
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        )
        LaunchedEffect(Unit) {
            delay(500)
            progress = stat.start/100f
        }

        Text(
            text = stat.name,
            modifier= Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .weight(2f)
                .padding(start = 8.dp, end = 8.dp)
                .height(8.dp),
            color = color,
            trackColor = Color.Gray,
            strokeCap = StrokeCap.Round
        )
        Text(
            text = "${stat.start} %",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemStatePreview() {
    MyPokedexTheme {
        ItemState(StatModel("HP",40), Color.Blue)
    }

}