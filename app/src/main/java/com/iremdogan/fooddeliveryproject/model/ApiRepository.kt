package com.iremdogan.fooddeliveryproject.model

import com.example.fooddeliveryapp.model.entity.register.RegisterRequest
import com.iremdogan.fooddeliveryproject.model.entity.login.LoginRequest
import com.iremdogan.fooddeliveryproject.model.local.LocalDataSource
import com.iremdogan.fooddeliveryproject.model.remote.RemoteDataSource
import com.iremdogan.fooddeliveryproject.utils.performAuthTokenNetworkOperation
import com.iremdogan.fooddeliveryproject.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
){
/*
    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }

    fun getRestaurantByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }

    fun getOrder() =
        performNetworkOperation {
            remoteDataSource.getOrders()
        }

    fun getUser() = performNetworkOperation {
        remoteDataSource.getUser()
    }

    fun updateUser(user : UserRequest) = performNetworkOperation {
        remoteDataSource.updateUser(request = user)
    }

    fun postOrder(orderAddRequest: OrderAddRequest) =
        performNetworkOperation {
            remoteDataSource.postOrder(orderAddRequest)
        }

    fun logOut() {
        localDataSource.saveToken("")
    }
 */
}