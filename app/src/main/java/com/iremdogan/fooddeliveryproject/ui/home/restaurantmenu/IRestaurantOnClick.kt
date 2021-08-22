package com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu

import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData

interface IRestaurantOnClick {
    fun onClick(restaurant: RestaurantData)
}