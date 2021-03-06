package com.iremdogan.fooddeliveryproject.model.remote

import com.iremdogan.fooddeliveryproject.model.entity.cart.CartResponse
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealListResponse
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealResponse
import com.iremdogan.fooddeliveryproject.model.entity.order.OrderResponse
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantListResponse
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantResponse
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthAPIService {

    @GET("rest/restaurants")
    suspend fun getRestaurants() : Response<RestaurantListResponse>

    @GET("rest/restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id : Long) : Response<RestaurantResponse>

    @GET("rest/restaurants-cuisine/{cuisine}")
    suspend fun getRestaurantByCuisine(@Path("cuisine") cuisine : String) : Response<RestaurantListResponse>

    @GET("rest/restaurant/meals/{restaurant_id}")
    suspend fun getRestaurantMeals(@Path("restaurant_id") restaurant_id : Long) : Response<MealListResponse>

    @GET("rest/meals/{meal_id}")
    suspend fun getMealDetail(@Path("meal_id") meal_id : Long) : Response<MealResponse>

    @GET("rest/cart")
    suspend fun getCart() : Response<CartResponse>

    @PUT("rest/cart/add")
    suspend fun addToCart(@Query("meal_id") meal_id: Long, @Query("count") count: Long) : Response<CartResponse>

    @DELETE("rest/cart/remove")
    suspend fun removeItemFromCart(@Path("meal_id") meal_id: Long, @Path("count") count: Long) : Response<CartResponse>

    @PUT("rest/orders/create")
    suspend fun createOrder() : Response<OrderResponse>

    @GET("rest/orders")
    suspend fun getUserLastOrders() : Response<OrderResponse>

    @GET("rest/user")
    suspend fun getUserDetails() : Response<UserResponse>

    @POST("rest/user/update")
    suspend fun updateUserDetails(@Body request: UserRequest) : Response<UserResponse>

}