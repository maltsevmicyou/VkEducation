package io.mmaltsev.vkeducation.presentation.applist


sealed class Screen(val route: String) {
    object AppList : Screen("app_list")
    object AppDetails : Screen("app_details")
}
