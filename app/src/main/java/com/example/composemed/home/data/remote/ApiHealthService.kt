package com.example.composemed.home.data.remote


import com.example.composemed.home.data.remote.models.ApiResponse
import retrofit2.http.GET


interface ApiHealthService {
    @GET("v3/14eeeebb-64be-47df-86c9-fb96efa380a7")
    suspend fun getMedications(): ApiResponse
}




