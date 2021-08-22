package com.iremdogan.fooddeliveryproject.model

import com.iremdogan.fooddeliveryproject.model.entity.login.LoginRequest
import com.iremdogan.fooddeliveryproject.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.user.UserRequest
import com.iremdogan.fooddeliveryproject.model.local.LocalDataSource
import com.iremdogan.fooddeliveryproject.model.remote.RemoteDataSource
import com.iremdogan.fooddeliveryproject.utils.performAuthTokenNetworkOperation
import com.iremdogan.fooddeliveryproject.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
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

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.register(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: Long) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun getRestaurantsByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }

    fun getMealDetail(id: Long) =
        performNetworkOperation {
            remoteDataSource.getMealDetail(id)
        }

    fun getCart() =
        performNetworkOperation {
            remoteDataSource.getCart()
        }

    fun addToCart(id: Long, count: Long) = performNetworkOperation {
        remoteDataSource.addToCart(id, count)
    }

    fun removeItemFromCart(id: Long, count: Long) = performNetworkOperation {
        remoteDataSource.removeItemFromCart(id, count)
    }

    fun createOrder() =
        performNetworkOperation {
            remoteDataSource.createOrder()
        }

    fun getUserLastOrders() = performNetworkOperation {
        remoteDataSource.getUserLastOrders()
    }

    fun getUserDetails() =
        performNetworkOperation {
            remoteDataSource.getUserDetails()
        }

    fun updateUserDetails(userRequest: UserRequest) =
        performNetworkOperation {
            remoteDataSource.updateUserDetails(userRequest)
        }

    fun logOut() {
        localDataSource.saveToken("")
    }

}