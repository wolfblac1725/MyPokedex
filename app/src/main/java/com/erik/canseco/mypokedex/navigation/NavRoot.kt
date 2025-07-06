package com.erik.canseco.mypokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.erik.canseco.mypokedex.presentation.screen.detail.DetailScreenRoot
import com.erik.canseco.mypokedex.presentation.screen.home.HomeScreenRoot

@Composable
fun NavRoot(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home> {
            HomeScreenRoot(
                modifier = modifier,
                onItemClick = { url ,name ,color ->
                    navController.navigate(Detail(url,name,color))
                }
            )
        }
        composable<Detail> { backStackEntry ->
            val dataDetail: Detail = backStackEntry.toRoute()
            DetailScreenRoot(
                modifier = modifier,
                id = dataDetail.id,
                name = dataDetail.name,
                color = dataDetail.color,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}