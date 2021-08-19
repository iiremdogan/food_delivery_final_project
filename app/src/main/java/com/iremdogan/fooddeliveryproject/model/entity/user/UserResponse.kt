package com.iremdogan.fooddeliveryproject.model.entity.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val userData: UserData,
    @SerializedName("success")
    val success: Boolean
)
