package io.mmaltsev.vkeducation.data.appdetails.network

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String
    // Если сервер вернет дополнительные поля, их можно будет добавить сюда позже
)