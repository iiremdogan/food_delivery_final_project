package com.iremdogan.fooddeliveryproject.model.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("jwtToken")
    val jwtToken: String
)