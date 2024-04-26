package com.eshopthis.finds.API

import com.eshopthis.finds.data.ApiResponse
import com.eshopthis.finds.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // ...

    @POST("checkusername")
    suspend fun checkUsername(@Body username: String): Response<ApiResponse<Boolean>>

    @POST("signup")
    suspend fun registerUser(@Body user: User): Response<ApiResponse<User>>

    @POST("loginuser")
    suspend fun loginUser(@Body user: User): Response<ApiResponse<User>>

    @POST("updateuser")
    suspend fun updateUser(@Body user: User): Response<ApiResponse<User>>

    @POST("resetpassword")
    suspend fun resetPassword(@Body user: User): Response<ApiResponse<Boolean>>

    @POST("deleteuser")
    suspend fun deleteUser(@Body user: User): Response<ApiResponse<Boolean>>
}