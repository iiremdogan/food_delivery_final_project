package com.iremdogan.fooddeliveryproject.ui.cart

import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

interface ICartOnClick {
    fun onClickIncreaseButton(meal: MealData)
    fun onClickDecreaseButton(meal: MealData)
}