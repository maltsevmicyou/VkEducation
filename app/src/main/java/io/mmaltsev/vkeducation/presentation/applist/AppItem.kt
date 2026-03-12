package io.mmaltsev.vkeducation.presentation.applist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.mmaltsev.vkeducation.R

data class AppItem(
    val title: String,
    val subtitle1: String,
    val subtitle2: String,
    val imageRes: Int,
    val iconColor: Color,
    val onClick: () -> Unit
)

val appItems = listOf(
    AppItem(
        title = "Сбербанк Онлайн",
        subtitle1 = "Вход в банк",
        subtitle2 = "С аккаунтом",
        imageRes = R.drawable.ic_sberbank,
        iconColor = Color.Green,
        onClick = { println("Сбер!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    ),
    AppItem(
        title = "Крутое приложение",
        subtitle1 = "Очень",
        subtitle2 = "Очень",
        imageRes = R.drawable.ic_mock,
        iconColor = Color.Green,
        onClick = { println("Mock!") }
    )

)
