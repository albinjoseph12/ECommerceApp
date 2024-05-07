package com.eshopthis.finds.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eshopthis.finds.data.ApiResponse
import com.eshopthis.finds.data.AppDatabase
import com.eshopthis.finds.data.ItemDAO
import com.eshopthis.finds.models.Item
import com.eshopthis.finds.models.User
import com.eshopthis.finds.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(application: Application, private val userRepository: UserRepository, private val itemDao: ItemDAO) : AndroidViewModel(application) {

    private val appDatabase: AppDatabase = AppDatabase.getInstance(application)

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

    // Item Database Operations
    suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    // API Operations

    fun checkUsername(username: String, callback: (Response<ApiResponse<Boolean>>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.checkUsername(username)
            callback(response)
        }
    }

    fun registerUser(user: User, callback: (Response<ApiResponse<User>>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.registerUser(user)
            callback(response)
        }
    }

    fun loginUser(user: User, callback: (Response<ApiResponse<User>>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.loginUser(user)
            callback(response)
        }
    }

    fun updateUserApi(user: User, callback: (Response<ApiResponse<User>>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.updateUserApi(user)
            callback(response)
        }
    }

    fun resetPassword(user: User, callback: (Response<ApiResponse<Boolean>>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.resetPassword(user)
            callback(response)
        }
    }

    fun deleteUserApi(user: User, callback: (Response<ApiResponse<Boolean>>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.deleteUserApi(user)
            callback(response)
        }
    }
}