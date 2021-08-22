package com.iremdogan.fooddeliveryproject.ui.restaurantdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealListResponse
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getRestaurantDetail(id : Long) : LiveData<Resource<RestaurantResponse>> = apiRepository.getRestaurantById(id)

}