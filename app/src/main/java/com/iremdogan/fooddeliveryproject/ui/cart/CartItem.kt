package com.iremdogan.fooddeliveryproject.ui.cart

data class CartItem(
    val imageView: String,
    val restaurantName: String,
    val mealName: String,
    val mealPrice: String,
    var count: Int
)
