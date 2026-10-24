package io.mmaltsev.vkeducation.domain.appdetails

data class AppItem(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconResId: Int
)