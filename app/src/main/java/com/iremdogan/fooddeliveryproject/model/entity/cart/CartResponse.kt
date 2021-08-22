package com.iremdogan.fooddeliveryproject.model.entity.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("responseBody")
    val cartData: CartData,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("message")
    val message: String
)
