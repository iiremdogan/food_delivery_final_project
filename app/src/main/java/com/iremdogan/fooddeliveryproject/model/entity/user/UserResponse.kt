package com.iremdogan.fooddeliveryproject.model.entity.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("responseBody")
    val userData: UserData,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)
