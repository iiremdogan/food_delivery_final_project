package com.iremdogan.fooddeliveryproject.model.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("responseBody")
    val registerData: RegisterData,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)

