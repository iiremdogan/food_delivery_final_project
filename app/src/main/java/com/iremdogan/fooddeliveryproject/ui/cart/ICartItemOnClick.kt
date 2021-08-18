package com.iremdogan.fooddeliveryproject.ui.cart

interface ICartItemOnClick {
    fun onDecreaseButtonClick(cartItem: CartItem)
    fun onIncreaseButtonClick(cartItem: CartItem)
}