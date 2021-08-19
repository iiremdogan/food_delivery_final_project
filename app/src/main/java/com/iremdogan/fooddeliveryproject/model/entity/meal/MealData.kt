package com.iremdogan.fooddeliveryproject.model.entity.meal

import com.google.gson.annotations.SerializedName

data class MealData(
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String
)
