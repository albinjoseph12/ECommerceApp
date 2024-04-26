package com.eshopthis.finds.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.eshopthis.finds.models.User
import com.eshopthis.finds.repository.Resource
import com.eshopthis.finds.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun loginUser(username: String, password: String): LiveData<Resource<User>> {
        return liveData {
            emit(Resource.loading())
            val result = userRepository.loginUser(username, password)
            emitSource(result)
        }
    }

    fun registerUser(user: User): LiveData<Resource<User>> {
        return liveData {
            emit(Resource.loading())
            val result = userRepository.registerUser(user)
            emitSource(result)
        }
    }

    fun checkUsername(username: String): LiveData<Resource<Boolean>> {
        return liveData {
            emit(Resource.loading())
            val result = userRepository.checkUsername(username)
            emitSource(result)
        }
    }
}