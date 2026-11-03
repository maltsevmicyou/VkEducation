package com.vk.simplerepoexample.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class App(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)