package io.mmaltsev.vkeducation.data.appdetails.network

import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppCatalogDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto

    suspend fun getRating(id: String): Float {
        delay(1500) // Эмуляция сетевого запроса
        return (30..50).random() / 10f
    }
}