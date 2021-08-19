package com.iremdogan.fooddeliveryproject.model.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String
)