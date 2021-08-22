package com.iremdogan.fooddeliveryproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantListResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getRestaurants() : LiveData<Resource<RestaurantListResponse>> = apiRepository.getRestaurants()

    fun getRestaurantsByCuisine(cuisine: String) : LiveData<Resource<RestaurantListResponse>> = apiRepository.getRestaurantsByCuisine(cuisine)

}