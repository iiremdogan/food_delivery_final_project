package com.iremdogan.fooddeliveryproject.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.cart.CartResponse
import com.iremdogan.fooddeliveryproject.model.entity.order.OrderResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {

    var cartItemCount : MutableLiveData<Int> ?= null

    fun getCart(): LiveData<Resource<CartResponse>> = apiRepository.getCart()

    fun addToCart(id: Long, count: Long): LiveData<Resource<CartResponse>> = apiRepository.addToCart(id, count)

    fun removeItemFromCart(id: Long, count: Long): LiveData<Resource<CartResponse>> = apiRepository.removeItemFromCart(id, count)

    fun createOrder() : LiveData<Resource<OrderResponse>> = apiRepository.createOrder()

    fun increaseCartItemCount(): LiveData<Int> {
        cartItemCount?.value?.plus(1)
        return cartItemCount!!
    }

    fun decreaseCartItemCount(): LiveData<Int> {
        cartItemCount?.value?.minus(1)
        return cartItemCount!!
    }

    fun setCartItemCount(count: Int) : LiveData<Int> {
        cartItemCount?.value = 1
        return cartItemCount!!
    }

    fun getCartItemCount() : LiveData<Int> = cartItemCount!!

}