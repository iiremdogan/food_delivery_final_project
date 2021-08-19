package com.iremdogan.fooddeliveryproject.model.entity.user

import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.order.OrderData

data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("lastorders")
    val lastOrders: List<OrderData>
)
