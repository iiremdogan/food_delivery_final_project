package com.iremdogan.fooddeliveryproject.ui.restaurantdetail

data class MealModel(
    val image: String,
    val name: String,
    val ingredients: List<String>,
    val price: String
)
