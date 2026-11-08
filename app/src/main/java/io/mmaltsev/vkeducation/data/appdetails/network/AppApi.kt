package io.mmaltsev.vkeducation.data.appdetails.network

import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    // Запрос каталога приложений
    @GET("catalog")
    suspend fun getCatalog(): List<AppCatalogDto>

    // Запрос информации о конкретном приложении по ID (интегрировать пока не нужно, но реализовать надо)
    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}