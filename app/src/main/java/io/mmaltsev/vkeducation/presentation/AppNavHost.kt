package io.mmaltsev.vkeducation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen
import io.mmaltsev.vkeducation.presentation.applist.SquadListScreen

object Routes{
    const val APP_LIST = "app_list"
    const val APP_DETAILS = "app_deteils"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.APP_LIST,
        modifier = modifier
    ){
        composable(Routes.APP_LIST){
            SquadListScreen(
                onAppClick = {
                    navController.navigate(Routes.APP_DETAILS)
                },
            )
        }
        composable(Routes.APP_DETAILS){
            _root_ide_package_.io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onShowcaseAppClick = {
                    navController.navigate(Routes.APP_LIST) {
                        popUpTo(Routes.APP_LIST)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}