package com.eshopthis.finds.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eshopthis.finds.API.ApiService
import com.eshopthis.finds.data.ApiResponse
import com.eshopthis.finds.di.UserDao
import com.eshopthis.finds.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val apiService: ApiService, private val userDao: UserDao) {

    suspend fun loginUser(username: String, password: String): LiveData<Resource<User>> {
        val result = MutableLiveData<Resource<User>>()
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.loginUser(User(username = username, password = password))
                // Immediately log the full response body
                Log.d("Login", "Full Response Body: ${response.body()}")

                // Log the full response body for successful HTTP responses
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("Login", "Full Response Body: $responseBody") // Log the response body
                    val apiResponse = processResponse(response, User::class.java)
                    result.postValue(apiResponse)
                } else {
                    // Log the full error body for unsuccessful HTTP responses
                    val errorBody = response.errorBody()?.string()
                    Log.d("Login", "Full Error Body: $errorBody") // Log the error body
                    result.postValue(Resource.error("HTTP Error: ${response.code()} - $errorBody"))
                }
            } catch (e: Exception) {
                Log.e("Login", "Login exception: ${e.localizedMessage}", e)
                result.postValue(Resource.error("Network exception: ${e.localizedMessage}"))
            }
        }
        return result
    }



    suspend fun registerUser(user: User): LiveData<Resource<User>> {
        val result = MutableLiveData<Resource<User>>()
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.registerUser(user)
                result.postValue(processResponse(response, User::class.java))
            } catch (e: Exception) {
                result.postValue(Resource.error("Exception occurred: ${e.localizedMessage}"))
                Log.e("UserRepository", "Exception during registration: ${e.localizedMessage}")
            }
        }
        return result
    }

    suspend fun checkUsername(username: String): LiveData<Resource<Boolean>> {
        val result = MutableLiveData<Resource<Boolean>>()
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.checkUsername(username)
                result.postValue(processResponse(response, Boolean::class.java))
            } catch (e: Exception) {
                result.postValue(Resource.error("Exception occurred: ${e.localizedMessage}"))
                Log.e("UserRepository", "Exception during username check: ${e.localizedMessage}")
            }
        }
        return result
    }
    private fun <T> processResponse(response: Response<ApiResponse<T>>, dataClass: Class<T>): Resource<T> {
        return if (response.isSuccessful) {
            val apiResponse = response.body()
            if (apiResponse != null) {
                if (apiResponse.success) {
                    val data = apiResponse.data
                    if (data != null && dataClass.isInstance(data)) {
                        Resource.success(data)
                    } else {
                        Resource.error("Invalid data type")
                    }
                } else {
                    // Use the message from the ApiResponse for the error message
                    Resource.error(apiResponse.message)
                }
            } else {
                Resource.error("Empty response body")
            }
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = "HTTP Error: ${response.code()} - $errorBody"
            Resource.error(errorMessage)
        }
    }



    suspend fun saveUserToDatabase(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(message: String, data: T? = null): Resource<T> = Resource(Status.ERROR, data, message)
        fun <T> loading(data: T? = null): Resource<T> = Resource(Status.LOADING, data, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}