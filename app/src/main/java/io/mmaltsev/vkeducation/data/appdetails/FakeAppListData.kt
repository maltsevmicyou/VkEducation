package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.R

// Хардкод данные в формате DTO
val fakeAppListDto = listOf(
    AppItemDto("1", "VK Клипы", "Короткие видео на любые темы", "Развлечения", R.drawable.ic_launcher_foreground),
    AppItemDto("2", "VK Музыка", "Миллионы треков и плейлистов", "Музыка", R.drawable.ic_launcher_foreground),
    AppItemDto("3", "VK Мессенджер", "Быстрые и безопасные сообщения", "Общение", R.drawable.ic_launcher_foreground)
)

// Для витрины (9 приложений)
val fakeShowcaseDto = listOf(
    AppItemDto("4", "VK Почта", "Удобная работа с письмами", "Работа", R.drawable.ic_launcher_foreground),
    AppItemDto("5", "VK Звонки", "Качественные аудио и видеозвонки", "Общение", R.drawable.ic_launcher_foreground),
    AppItemDto("6", "VK Знакомства", "Находи новых друзей рядом", "Знакомства", R.drawable.ic_launcher_foreground),
    AppItemDto("7", "VK Mini Apps", "Мини-приложения внутри VK", "Игры", R.drawable.ic_launcher_foreground),
    AppItemDto("8", "VK ID", "Единый аккаунт для всех сервисов", "Безопасность", R.drawable.ic_launcher_foreground),
    AppItemDto("9", "VK Pay", "Быстрые и безопасные платежи", "Финансы", R.drawable.ic_launcher_foreground),
    AppItemDto("10", "VK Работа", "Поиск вакансий и подработка", "Карьера", R.drawable.ic_launcher_foreground),
    AppItemDto("11", "VK Образование", "Курсы и вебинары", "Обучение", R.drawable.ic_launcher_foreground),
    AppItemDto("12", "VK Здоровье", "Забота о здоровье и спорт", "Здоровье", R.drawable.ic_launcher_foreground)
)