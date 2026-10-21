package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- 1. МОДЕЛЬ ДАННЫХ И СПИСОК (если их нет в других файлах) ---
data class AppInfo(val name: String, val description: String, val category: String)

val sampleApps = listOf(
    AppInfo("VK", "Социальная сеть", "Соцсети"),
    AppInfo("Telegram", "Мессенджер", "Общение"),
    AppInfo("YouTube", "Видео", "Развлечения"),
    AppInfo("Яндекс", "Поиск", "Инструменты")
)

// --- 2. ЭКРАН СПИСКА (ГЛАВНЫЙ) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(onAppClick: (AppInfo) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список приложений") },
                navigationIcon = {
                    Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.padding(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding), contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(sampleApps) { app ->
                AppListItem(app = app, modifier = Modifier.clickable { onAppClick(app) })
            }
        }
    }
}

// Переиспользуемая карточка приложения
@Composable
fun AppListItem(app: AppInfo, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(app.name, style = MaterialTheme.typography.titleMedium)
                Text(app.description, style = MaterialTheme.typography.bodyMedium)
                Text("Категория: ${app.category}", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}

// --- 3. ЭКРАН ДЕТАЛЕЙ С ВИТРИНОЙ ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(app: AppInfo, onStoreAppClick: () -> Unit) {
    // Захардкоженные данные для витрины (9 приложений)
    val storeApps = List(9) { index -> AppInfo("Витрина App ${index + 1}", "Описание ${index + 1}", "Категория") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(app.name) }) }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            // Имитация контента карточки
            Text("Описание: ${app.description}", modifier = Modifier.padding(16.dp))
            Text("Категория: ${app.category}", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(32.dp))

            // ВИТРИНА В САМОМ НИЗУ
            Text("Витрина", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

            val pagerState = rememberPagerState(pageCount = { 3 }) // 3 страницы

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
                // Получаем 3 приложения для текущей страницы
                val pageApps = storeApps.drop(page * 3).take(3)

                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    pageApps.forEach { storeApp ->
                        // Используем ту же карточку, что и на главном экране!
                        AppListItem(app = storeApp, modifier = Modifier.clickable { onStoreAppClick() })
                    }
                }
            }
        }
    }
}