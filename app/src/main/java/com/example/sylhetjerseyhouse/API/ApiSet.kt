package com.example.sylhetjerseyhouse.API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiSet {
    @FormUrlEncoded
    @POST("login_user.php")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Data>
}