package com.iremdogan.fooddeliveryproject.model.entity.meal

import com.google.gson.annotations.SerializedName

data class MealListResponse(
    @SerializedName("responseBody")
    val mealData: List<MealData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)
