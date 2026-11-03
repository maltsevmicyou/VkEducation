package com.vk.simplerepoexample.data

import com.vk.simplerepoexample.data.dto.App
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/catalog")
    fun getApps(): Call<List<App>>

}