package io.mmaltsev.vkeducation.presentation.applist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme


// ---------- Экран: тулбар + список ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SquadListScreen(
    onAppClick: (SquadInfo) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Отряды Спб") },
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_logo_spbso),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(32.dp),
                        tint = Color.Unspecified,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
    ) { padding ->
        SquadList(squads = squads, onAppClick = onAppClick, modifier = Modifier.padding(padding))
    }
}

@Composable
fun SquadList(squads: List<SquadInfo>, onAppClick: (SquadInfo) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(squads) { squad -> SquadListItem(squad = squad, onClick = {onAppClick(squad)}) }
    }
}

@Preview(showBackground = true)
@Composable
fun SquadListScreenPreview() {
    VkEducationTheme {
        SquadListScreen(onAppClick = {})
    }
}
