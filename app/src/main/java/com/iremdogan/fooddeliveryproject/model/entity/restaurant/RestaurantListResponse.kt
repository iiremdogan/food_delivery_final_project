package com.iremdogan.fooddeliveryproject.model.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantListResponse(
    @SerializedName("responseBody")
    val restaurantData: List<RestaurantData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)
