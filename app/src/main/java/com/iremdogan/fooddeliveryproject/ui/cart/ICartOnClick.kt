package com.iremdogan.fooddeliveryproject.ui.cart

import com.iremdogan.fooddeliveryproject.model.entity.cart.MealInfo

interface ICartOnClick {
    fun onClickIncreaseButton(meal: MealInfo)
    fun onClickDecreaseButton(meal: MealInfo)
}