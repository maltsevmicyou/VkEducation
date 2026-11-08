package io.mmaltsev.vkeducation.data.appdetails.network

import kotlinx.serialization.Serializable

@Serializable
data class AppCatalogDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String
)