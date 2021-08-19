package com.iremdogan.fooddeliveryproject.model.entity.restaurant

import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

data class RestaurantData(
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("meals")
    val meals: List<MealData>,
    @SerializedName("name")
    val name: String,
    @SerializedName("cuisine")
    val cuisine: String,
    @SerializedName("information")
    val information: String,
    @SerializedName("minDeliveryTime")
    val minDeliveryTime: String,
    @SerializedName("minDeliveryFee")
    val minDeliveryFee: String
)
