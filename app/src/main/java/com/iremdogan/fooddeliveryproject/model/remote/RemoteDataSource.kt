package com.iremdogan.fooddeliveryproject.model.remote

import com.iremdogan.fooddeliveryproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiAPIService: APIService) : BaseDataSource(){

    /*
    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun getRestaurantsByCuisine(cuisine: String) =
        getResult { apiService.getRestaurantsByCuisine(cuisine) }
     */
}