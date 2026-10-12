package io.mmaltsev.vkeducation.data.appdetails

import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}