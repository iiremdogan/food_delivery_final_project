package com.iremdogan.fooddeliveryproject.ui.cart

import android.widget.ImageView

data class CartItem(
    val imageView: ImageView,
    val restaurantName: String,
    val mealName: String,
    val mealPrice: String,
    val count: Int
)
