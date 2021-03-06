package com.iremdogan.fooddeliveryproject.model.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("responseBody")
    val orderData: List<OrderData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)