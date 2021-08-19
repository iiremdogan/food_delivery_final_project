package com.iremdogan.fooddeliveryproject.model.entity.meal


import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("data")
    val mealData: List<MealData>,
    @SerializedName("success")
    val success: Boolean
)