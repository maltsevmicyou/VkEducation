package io.mmaltsev.vkeducation.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen
import io.mmaltsev.vkeducation.presentation.appdetails.AppListScreen

object Destinations {
    const val APP_LIST = "app_list"
    const val APP_DETAILS = "app_details"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.APP_LIST) {

        composable(Destinations.APP_LIST) {
            AppListScreen(
                onAppClick = { appId ->
                    // Клик в списке -> переход на детали
                    navController.navigate("${Destinations.APP_DETAILS}/$appId")
                }
            )
        }

        composable("${Destinations.APP_DETAILS}/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")
            AppDetailsScreen(
                appId = appId,
                onShowcaseClick = {
                    // Клик в витрине -> переход на список (с очисткой стека, чтобы не плодить экраны)
                    navController.navigate(Destinations.APP_LIST) {
                        popUpTo(Destinations.APP_LIST) { inclusive = true }
                    }
                }
            )
        }
    }
}