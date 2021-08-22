package com.iremdogan.fooddeliveryproject.model

import com.iremdogan.fooddeliveryproject.model.entity.login.LoginRequest
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.model.local.LocalDataSource
import com.iremdogan.fooddeliveryproject.model.remote.AuthRemoteDataSource
import com.iremdogan.fooddeliveryproject.model.remote.RemoteDataSource
import com.iremdogan.fooddeliveryproject.utils.performAuthTokenNetworkOperation
import com.iremdogan.fooddeliveryproject.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var authRemoteDataSource: AuthRemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.login(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun register(request: RegisterRequest) = performNetworkOperation{
        remoteDataSource.register(request)
        }


    fun getRestaurants() =
        performNetworkOperation {
            authRemoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: Long) =
        performNetworkOperation {
            authRemoteDataSource.getRestaurantById(id)
        }

    fun getRestaurantMeals(id: Long) =
        performNetworkOperation {
            authRemoteDataSource.getRestaurantMeals(id)
        }

    fun getRestaurantsByCuisine(cuisine: String) =
        performNetworkOperation {
            authRemoteDataSource.getRestaurantsByCuisine(cuisine)
        }

    fun getMealDetail(id: Long) =
        performNetworkOperation {
            authRemoteDataSource.getMealDetail(id)
        }

    fun getCart() =
        performNetworkOperation {
            authRemoteDataSource.getCart()
        }

    fun addToCart(id: Long, count: Long) = performNetworkOperation {
        authRemoteDataSource.addToCart(id, count)
    }

    fun removeItemFromCart(id: Long, count: Long) = performNetworkOperation {
        authRemoteDataSource.removeItemFromCart(id, count)
    }

    fun createOrder() =
        performNetworkOperation {
            authRemoteDataSource.createOrder()
        }

    fun getUserLastOrders() = performNetworkOperation {
        authRemoteDataSource.getUserLastOrders()
    }

    fun getUserDetails() =
        performNetworkOperation {
            authRemoteDataSource.getUserDetails()
        }

    fun updateUserDetails(userRequest: UserRequest) =
        performNetworkOperation {
            authRemoteDataSource.updateUserDetails(userRequest)
        }

}