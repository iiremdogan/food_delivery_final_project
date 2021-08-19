package com.iremdogan.fooddeliveryproject.model.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("data")
    val restaurantData: List<RestaurantData>,
    @SerializedName("success")
    val success: Boolean
)
