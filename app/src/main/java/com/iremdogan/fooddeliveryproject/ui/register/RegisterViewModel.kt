package com.iremdogan.fooddeliveryproject.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun register(
        name: String,
        surname: String,
        username: String,
        email: String,
        password: String
    ): LiveData<Resource<RegisterResponse>> {
        val request = RegisterRequest(name, surname, username, email, password)
        return apiRepository.register(request)
    }

}