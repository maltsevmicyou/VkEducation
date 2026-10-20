package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.domain.appdetails.AppItem

val hardcodedApps = listOf(
    AppItem(
        id = "1",
        name = "VK Клипы",
        description = "Короткие видео на любые темы от популярных авторов",
        category = "Развлечения",
        iconResId = R.drawable.ic_launcher_foreground // Стандартная иконка Android
    ),
    AppItem(
        id = "2",
        name = "VK Музыка",
        description = "Миллионы треков, плейлисты и подкасты в одном месте",
        category = "Музыка",
        iconResId = R.drawable.ic_launcher_foreground
    ),
    AppItem(
        id = "3",
        name = "VK Мессенджер",
        description = "Быстрые, бесплатные и безопасные сообщения",
        category = "Общение",
        iconResId = R.drawable.ic_launcher_foreground
    )
)