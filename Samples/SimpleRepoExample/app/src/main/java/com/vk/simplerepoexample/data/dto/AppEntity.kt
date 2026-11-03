package com.vk.simplerepoexample.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)