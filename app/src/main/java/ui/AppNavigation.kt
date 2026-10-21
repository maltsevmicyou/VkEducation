package ui



import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    // Контроллер навигации (как пульт от телевизора)
    val navController = rememberNavController()

    // NavHost - это контейнер, где меняются экраны
    NavHost(
        navController = navController,
        startDestination = "app_list" // С какого экрана начинаем
    ) {
        // ЭКРАН 1: Список приложений (из прошлого ДЗ)
        composable("app_list") {
            AppListScreen(
                onAppClick = { app ->
                    // По клику переходим на детали, передавая имя приложения
                    navController.navigate("app_details/${app.name}")
                }
            )
        }

        // ЭКРАН 2: Детали приложения с Витриной
        composable("app_details/{appName}") { backStackEntry ->
            // Достаем имя приложения, которое передали при клике
            val appName = backStackEntry.arguments?.getString("appName")

            // Находим это приложение в нашем списке (предполагаем, что sampleApps доступен)
            val app = sampleApps.find { it.name == appName } ?: sampleApps.first()

            AppDetailsScreen(
                app = app,
                onStoreAppClick = {
                    // Клик по витрине ведет обратно на главный экран
                    navController.navigate("app_list") {
                        // Очищаем стек, чтобы кнопка "Назад" не вводила в ступор
                        popUpTo("app_list") { inclusive = true }
                    }
                }
            )
        }
    }
}