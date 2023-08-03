package com.example.sylhetjerseyhouse.API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @POST("login_user.php")
    suspend fun loginUser(@Body postData: PostData): Response<ApiResponse>


    @POST("signup_user.php")
    suspend fun registerUser(@Body postData: PostData): Response<ApiResponse>

}