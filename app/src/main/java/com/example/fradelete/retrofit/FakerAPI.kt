package com.example.fradelete.retrofit

import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface FakerAPI {

    @GET("products")
    suspend fun getProducts(): Response<List<JSONObject>>

}