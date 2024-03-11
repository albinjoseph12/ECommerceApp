package com.eshopthis.finds.API

import com.eshopthis.finds.DataClass.ApiResponse
import com.eshopthis.finds.DataClass.GetProduct
import com.eshopthis.finds.DataClass.PostData
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
