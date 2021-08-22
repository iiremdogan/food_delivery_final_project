package com.iremdogan.fooddeliveryproject.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getUserInfo(): LiveData<Resource<UserResponse>> = apiRepository.getUserDetails()

    fun updateUserDetails(
        name: String,
        surname: String,
        email: String,
        username: String,
        password: String,
        address: String,
        phone: String
    ): LiveData<Resource<UserResponse>> {
        val request = UserRequest(name, surname, email, username, password, address, phone)
        return apiRepository.updateUserDetails(request)
    }

}