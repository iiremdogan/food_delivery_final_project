package com.iremdogan.fooddeliveryproject.ui.cart

import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

interface ICartOnClick {
    fun onClickIncreaseButton(cartItem: MealData)
    fun onClickDecreaseButton(cartItem: MealData)
}