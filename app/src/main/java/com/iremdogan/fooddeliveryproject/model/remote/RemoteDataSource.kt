package com.iremdogan.fooddeliveryproject.model.remote

import com.iremdogan.fooddeliveryproject.model.entity.login.LoginRequest
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource(){

    suspend fun login(loginRequest: LoginRequest) = getResult { apiService.login(loginRequest) }

    suspend fun register(registerRequest: RegisterRequest) = getResult { apiService.register(registerRequest) }

}