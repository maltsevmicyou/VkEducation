// AppInfo.kt
data class AppInfo(
    val name: String,
    val description: String,
    val category: String,
    // Пока используем заглушку для иконки, чтобы не усложнять
    val iconResId: Int // Сюда можно потом передать R.drawable.ic_launcher, но для теста сойдет и заглушка
)
