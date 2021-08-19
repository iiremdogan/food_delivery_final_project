package com.iremdogan.fooddeliveryproject.model.entity.user

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    val userName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String
)
