package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.domain.appdetails.Category
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.time.Duration.Companion.seconds

interface AppApi {
    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}