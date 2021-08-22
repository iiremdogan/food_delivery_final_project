package com.iremdogan.fooddeliveryproject.model.entity.user

import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.order.OrderData

data class UserData(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("lastOrders")
    val lastOrders: MutableList<OrderData>
)
