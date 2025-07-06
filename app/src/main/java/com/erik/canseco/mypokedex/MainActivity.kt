package com.erik.canseco.mypokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.erik.canseco.mypokedex.navigation.NavRoot
import com.erik.canseco.mypokedex.ui.theme.MyPokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                val navController = rememberNavController()
                NavRoot(
                    navController = navController,
                )
            }
        }
    }
}
