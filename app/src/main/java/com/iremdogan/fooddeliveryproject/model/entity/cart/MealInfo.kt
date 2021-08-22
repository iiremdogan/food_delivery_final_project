package com.iremdogan.fooddeliveryproject.model.entity.cart


import com.google.gson.annotations.SerializedName
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

data class MealInfo(
    @SerializedName("count")
    var count: Int,
    @SerializedName("mealInfo")
    val mealInfo: MealData
)