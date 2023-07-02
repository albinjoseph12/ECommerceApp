package com.example.sylhetjerseyhouse.API

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiController {
    private val url : String = "https://files.000webhost.com/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiInterface by lazy {
        retrofit.create(ApiSet::class.java)
    }



}