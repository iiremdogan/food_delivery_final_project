package com.iremdogan.fooddeliveryproject.model.entity.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("responseBody")
    val loginData: LoginData,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("message")
    val message: String
)