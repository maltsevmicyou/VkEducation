package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.presentation.applist.SquadInfo
import io.mmaltsev.vkeducation.presentation.applist.SquadListItem
import io.mmaltsev.vkeducation.presentation.applist.squads
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme

private const val APPS_PER_PAGE = 3

@Composable
fun Showcase(
    apps: List<SquadInfo>,
    onAppClick: (SquadInfo) -> Unit,
    modifier: Modifier = Modifier
){
    val pages = apps.chunked(APPS_PER_PAGE)
    val pagerState = rememberPagerState { pages.size }

    Column(modifier){
        Text(
            text = stringResource(R.string.app_details_showcase),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp,
            verticalAlignment = Alignment.Top,
        ) {
            pageIndex ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pages[pageIndex].forEach { app ->
                    SquadListItem(
                        squad = app,
                        onClick = {onAppClick(app)}
                    )
                }
            }
        }
    }
}

val showcaseApps = listOf(
    SquadInfo(R.drawable.ic_altair, "Альтаир", "Студенческий отряд направления ССервО", "ССервО"),
    SquadInfo(R.drawable.ic_artifex, "Артифекс", "Студенческий отряд направления САО", "САО"),
    SquadInfo(R.drawable.ic_django, "Джанго", "Студенческий отряд направления ССхО", "ССхО"),
    SquadInfo(R.drawable.ic_legenda, "Легенда", "Студенческий отряд направления СПО", "СПО"),
    SquadInfo(R.drawable.ic_los, "Лось", "Студенческий отряд направления ССхО", "ССхО"),
    SquadInfo(R.drawable.ic_plamya, "Пламя", "Студенческий отряд направления ССО", "ССО"),
    SquadInfo(R.drawable.ic_provorniy, "Проворный", "Студенческий отряд направления СОП", "СОП"),
    SquadInfo(R.drawable.ic_svarog, "Сварог", "Студенческий отряд направления СПрО", "СПрО"),
    SquadInfo(R.drawable.ic_orion, "Орион", "Студенческий отряд направления ССО", "ССО"),
)

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        Showcase(
            apps = showcaseApps,
            onAppClick = {},
        )
    }
}