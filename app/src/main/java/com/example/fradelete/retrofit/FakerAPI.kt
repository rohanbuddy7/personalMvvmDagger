package com.example.fradelete.retrofit

import org.json.JSONObject
import retrofit2.http.GET

interface FakerAPI {

    @GET("/products")
    suspend fun getProducts(): Result<JSONObject>

}