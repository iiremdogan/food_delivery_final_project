package com.iremdogan.fooddeliveryproject.model.remote

import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterResponse
import com.iremdogan.fooddeliveryproject.model.entity.login.LoginRequest
import com.iremdogan.fooddeliveryproject.model.entity.login.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @POST("public/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("public/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}