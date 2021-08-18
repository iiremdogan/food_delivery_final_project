package com.iremdogan.fooddeliveryproject.ui.cart

interface ICartOnClick {
    fun onClickIncreaseButton(cartItem: CartItem)
    fun onClickDecreaseButton(cartItem: CartItem)
}