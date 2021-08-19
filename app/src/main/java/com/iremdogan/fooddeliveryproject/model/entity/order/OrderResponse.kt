package com.iremdogan.fooddeliveryproject.model.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val orderData: List<OrderData>,
    @SerializedName("success")
    val success: Boolean
)