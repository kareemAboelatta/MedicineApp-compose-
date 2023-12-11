package com.example.composemed.data.remote


import com.example.composemed.data.models.ApiResponse
import retrofit2.http.GET


interface ApiHealthService {
    @GET("v3/34540dc6-ce19-44b7-8db1-26cd03df0fab")
    suspend fun getMedications(): ApiResponse
}




