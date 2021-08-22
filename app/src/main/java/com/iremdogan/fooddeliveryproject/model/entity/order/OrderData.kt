package com.iremdogan.fooddeliveryproject.model.entity.order

import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData

data class OrderData(
    @SerializedName("restaurantInfo")
    val restaurantData: RestaurantData,
    @SerializedName("mealInfoList")
    val mealInfoList: List<MealData>
)
