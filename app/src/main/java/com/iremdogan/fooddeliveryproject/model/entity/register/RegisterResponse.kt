package com.example.fooddeliveryapp.model.entity.register


import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterData

data class RegisterResponse(
    @SerializedName("data")
    val registerData: RegisterData,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)

