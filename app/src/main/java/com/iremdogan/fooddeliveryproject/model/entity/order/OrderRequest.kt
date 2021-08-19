package com.iremdogan.fooddeliveryproject.model.entity.order

import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData

data class OrderRequest(
    @SerializedName("restaurant")
    val restaurantData: RestaurantData,
    @SerializedName("meal")
    val mealData: MealData
)