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
import io.mmaltsev.vkeducation.domain.appdetails.AppItem

// Временная заглушка, чтобы код компилировался без старого хардкода
val showcaseApps = listOf(
    AppItem("s1", "App 1", "Desc 1", "Cat 1", "https://picsum.photos/200"),
    AppItem("s2", "App 2", "Desc 2", "Cat 2", "https://picsum.photos/200"),
    AppItem("s3", "App 3", "Desc 3", "Cat 3", "https://picsum.photos/200")
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowcaseBlock(onAppClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
        )

        val pagerState = rememberPagerState(pageCount = { 1 })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().height(110.dp)
        ) { page ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                showcaseApps.forEach { app ->
                    AppItemCard(
                        app = app,
                        modifier = Modifier.weight(1f),
                        onClick = onAppClick
                    )
                }
            }
        }
    }
}