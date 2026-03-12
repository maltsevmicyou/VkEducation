package io.mmaltsev.vkeducation.presentation.applist

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AppList.route
    ) {
        composable(Screen.AppList.route) {
            AppListScreen(navController = navController)
        }
        composable(Screen.AppDetails.route) {
            AppDetailsScreen()
        }
    }
}
