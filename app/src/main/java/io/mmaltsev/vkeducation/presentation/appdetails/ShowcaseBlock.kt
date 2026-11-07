package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.mmaltsev.vkeducation.data.appdetails.showcaseApps

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowcaseBlock(onAppClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            // 👇 ИСПРАВЛЕНО: используем start, end и bottom вместо horizontal + bottom
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
        )

        // Создаем состояние пейджера на 3 страницы
        val pagerState = rememberPagerState(pageCount = { 3 })

        // Горизонтальный пейджер
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().height(110.dp)
        ) { page ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val startIndex = page * 3
                val endIndex = minOf(startIndex + 3, showcaseApps.size)

                // Выводим по 3 приложения на страницу
                for (i in startIndex until endIndex) {
                    AppItemCard(
                        app = showcaseApps[i],
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onAppClick()
                        }
                    )
                }
            }
        }
    }
}