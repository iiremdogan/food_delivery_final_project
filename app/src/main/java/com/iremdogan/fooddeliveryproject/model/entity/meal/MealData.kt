package com.iremdogan.fooddeliveryproject.model.entity.meal

import com.google.gson.annotations.SerializedName

data class MealData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("count")
    val count: Long,
    @SerializedName("ingredients")
    val ingredients: List<String>
)
