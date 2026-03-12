package io.mmaltsev.vkeducation.presentation.applist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.mmaltsev.vkeducation.R.drawable.rustorelogowtext
import io.mmaltsev.vkeducation.presentation.theme.RuStoreBlue
import androidx.compose.material.icons.filled.GridView
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import io.mmaltsev.vkeducation.presentation.theme.LightBackground
import io.mmaltsev.vkeducation.presentation.theme.RuStoreBlueLight


@Composable
fun AppListScreen(navController: NavController) {
    Box(Modifier
        .background(RuStoreBlue)
        .fillMaxSize())
    {
        Column( verticalArrangement = Arrangement.spacedBy(25.dp) ) {

            Row(
                Modifier
                .padding(start = 15.dp, top = 50.dp, end = 15.dp)
                .fillMaxWidth())
            {
                Image(
                    painter = painterResource(id = rustorelogowtext),
                    contentDescription = "Лого",
                    modifier = Modifier.size(height = 46.dp, width = 150.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(RuStoreBlueLight)
                    .size(46.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = "Сетка",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                            .align(Alignment.Center)
                    )
                }
            } //конец ряда с лого и кнопками

            Box(Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(LightBackground)
                .fillMaxSize()
            ) {
                LazyColumn { //дата класс и лист айтемов лежат в файле AppItem.kt
                    items(appItems) { item ->
                        AppBlock(
                            title = item.title,
                            subtitle1 = item.subtitle1,
                            subtitle2 = item.subtitle2,
                            imageRes = item.imageRes,
                            iconColor = item.iconColor,
                            onClick = item.onClick,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}


