package com.example.sylhetjerseyhouse.API

import com.example.sylhetjerseyhouse.DataClass.ApiResponse
import com.example.sylhetjerseyhouse.DataClass.GetProduct
import com.example.sylhetjerseyhouse.DataClass.PostData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @POST("login_user.php")
    suspend fun loginUser(@Body postData: PostData): Response<ApiResponse>

    @POST("signup_user.php")
    suspend fun registerUser(@Body postData: PostData): Response<ApiResponse>

    @GET("products")
    suspend fun getProducts(): Response<List<GetProduct>>

}