package com.iremdogan.fooddeliveryproject.model.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("responseBody")
    val restaurantData: RestaurantData,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)
