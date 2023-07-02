package com.example.sylhetjerseyhouse.API

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiSet {
    @FormUrlEncoded
    @POST("login_user.php")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ApiResponse>

    @POST("signup_user.php")
    suspend fun postData(@Body requestData: RequestData) : Call<ApiResponse>

}