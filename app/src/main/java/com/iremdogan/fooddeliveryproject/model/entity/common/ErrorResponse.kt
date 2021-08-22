package com.iremdogan.fooddeliveryproject.model.entity.common

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)
