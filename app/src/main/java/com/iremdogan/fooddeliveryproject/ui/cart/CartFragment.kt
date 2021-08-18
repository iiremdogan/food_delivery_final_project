package com.iremdogan.fooddeliveryproject.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iremdogan.fooddeliveryproject.databinding.FragmentCartBinding
import com.iremdogan.fooddeliveryproject.utils.SwipeToDeleteCallback

class CartFragment : Fragment() {

    private lateinit var _binding: FragmentCartBinding
    private var cartAdapter = CartRecyclerViewAdapter()
    private var cartList: MutableList<CartItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.cartRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        initializeViews()
        initializeListeners()
    }

    private fun initializeViews() {
        cartList.add(CartItem("", "Restaurant Name","Meal Name", "48", 1))
        cartList.add(CartItem("", "Restaurant Name","Meal Name", "8", 2))
        cartList.add(CartItem("", "Restaurant Name","Meal Name", "1", 1))
        cartList.add(CartItem("", "Restaurant Name","Meal Name", "122", 1))

        _binding.cartRecyclerView.adapter = cartAdapter
        cartAdapter.setData(cartList)

        _binding.totalTextView.text = updateTotal().toString() + " TL"
    }

    private fun initializeListeners() {
        _binding.cartPaymentButton.setOnClickListener {
            //TODO : add order to user and navigate to home screen
        }

        cartAdapter.addListener(object : ICartOnClick{
            override fun onClickIncreaseButton(cartItem: CartItem) {
                _binding.totalTextView.text = updateTotal().toString() + " TL"
            }

            override fun onClickDecreaseButton(cartItem: CartItem) {
                _binding.totalTextView.text = updateTotal().toString() + " TL"
            }

        })

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                cartAdapter.removeAt(viewHolder.adapterPosition)
                _binding.totalTextView.text = updateTotal().toString() + " TL"
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(_binding.cartRecyclerView)
    }

    private fun updateTotal() : Int{
        var total = 0
        cartList.forEach {
            total += it.count * it.mealPrice.toInt()
        }
        return total
    }

}