package com.iremdogan.fooddeliveryproject.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.user.UserData
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel(){

    fun getUserInfo() : LiveData<Resource<UserResponse>> = apiRepository.getUserDetails()

//    fun updateUserInfo(
//        name: String,
//        surname: String,
//        email: String,
//        username: String,
//        password: String,
//        address: String,
//        phone: String
//    ) : LiveData<Resource<UserResponse>> {
//
//        // TODO: user'a çekidüzen verilecek
//        // TODO: cart'ın da düzenlenmesi lazım
//        val request : UserRequest(name,surname, email, username, password, address, phone)
//        return apiRepository.updateUserDetails(request)
//    }

}