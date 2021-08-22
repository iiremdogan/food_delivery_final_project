package com.iremdogan.fooddeliveryproject.model.entity.meal


import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("responseBody")
    val mealData: MealData,
    @SerializedName("message")
    val message: String,
    @SerializedName("reason")
    val reason: String
)