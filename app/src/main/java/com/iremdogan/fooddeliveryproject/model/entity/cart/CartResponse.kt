package com.iremdogan.fooddeliveryproject.model.entity.cart


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: Any,
    @SerializedName("responseBody")
    val responseBody: CartData
)