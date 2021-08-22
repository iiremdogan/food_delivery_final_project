package com.iremdogan.fooddeliveryproject.model.entity.login

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("jwtToken")
    val jwtToken: String
)