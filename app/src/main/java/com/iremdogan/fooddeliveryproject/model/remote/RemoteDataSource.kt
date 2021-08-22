package com.iremdogan.fooddeliveryproject.model.remote

import com.iremdogan.fooddeliveryproject.model.entity.login.LoginRequest
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource(){


    suspend fun login(loginRequest: LoginRequest) = getResult { apiService.login(loginRequest) }

    suspend fun register(registerRequest: RegisterRequest) = getResult { apiService.register(registerRequest) }

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun getRestaurantById(restaurantId: Long) = getResult { apiService.getRestaurantById(restaurantId) }

    suspend fun getRestaurantsByCuisine(cuisine: String) = getResult { apiService.getRestaurantByCuisine(cuisine) }

    suspend fun getRestaurantMeals(restaurantId: Long) = getResult { apiService.getRestaurantMeals(restaurantId) }

    suspend fun getMealDetail(mealId: Long) = getResult { apiService.getMealDetail(mealId) }

    suspend fun getCart() = getResult { apiService.getCart() }

    suspend fun addToCart(mealId: Long, count: Long) = getResult { apiService.addToCart(mealId, count) }

    suspend fun removeItemFromCart(mealId: Long, count: Long) = getResult { apiService.removeItemFromCart(mealId, count) }

    suspend fun getUserLastOrders() = getResult { apiService.getUserLastOrders() }

    suspend fun createOrder() = getResult { apiService.createOrder() }

    suspend fun getUserDetails() = getResult { apiService.getUserDetails() }

    suspend fun updateUserDetails(userRequest: UserRequest) = getResult { apiService.updateUserDetails(userRequest) }

}