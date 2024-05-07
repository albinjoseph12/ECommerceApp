package com.eshopthis.finds.repository

import com.eshopthis.finds.API.ApiService
import com.eshopthis.finds.data.ApiResponse
import com.eshopthis.finds.data.AppDatabase
import com.eshopthis.finds.models.User
import retrofit2.Response

class UserRepository(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService
) {
    // Local Database Operations
    suspend fun getUserByUsername(username: String): User? {
        return appDatabase.userDao().getUserByUsername(username)
    }

    suspend fun updateUser(user: User) {
        appDatabase.userDao().updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        appDatabase.userDao().deleteUser(user)
    }

    // API Operations
    suspend fun checkUsername(username: String): Response<ApiResponse<Boolean>> {
        return apiService.checkUsername(username)
    }

    suspend fun registerUser(user: User): Response<ApiResponse<User>> {
        return apiService.registerUser(user)
    }

    suspend fun loginUser(user: User): Response<ApiResponse<User>> {
        return apiService.loginUser(user)
    }

    suspend fun updateUserApi(user: User): Response<ApiResponse<User>> {
        return apiService.updateUser(user)
    }

    suspend fun resetPassword(user: User): Response<ApiResponse<Boolean>> {
        return apiService.resetPassword(user)
    }

    suspend fun deleteUserApi(user: User): Response<ApiResponse<Boolean>> {
        return apiService.deleteUser(user)
    }
}