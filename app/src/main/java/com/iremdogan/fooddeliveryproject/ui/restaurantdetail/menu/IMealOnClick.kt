package com.iremdogan.fooddeliveryproject.ui.restaurantdetail.menu

import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData

interface IMealOnClick {
    fun onClick(meal: MealData)
}