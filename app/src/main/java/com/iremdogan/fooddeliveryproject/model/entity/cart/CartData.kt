package com.iremdogan.fooddeliveryproject.model.entity.cart

import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData

data class CartData(
    @SerializedName("restaurantInfo")
    val restaurantData: RestaurantData,
    @SerializedName("mealInfoList")
    val mealInfoList: MutableList<MealData>
)
