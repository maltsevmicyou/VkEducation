package io.mmaltsev.vkeducation.data.appdetails

data class AppItemDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconResId: Int
)