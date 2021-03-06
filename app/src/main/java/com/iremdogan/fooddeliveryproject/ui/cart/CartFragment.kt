package com.iremdogan.fooddeliveryproject.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentCartBinding
import com.iremdogan.fooddeliveryproject.model.entity.cart.MealInfo
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData
import com.iremdogan.fooddeliveryproject.ui.MainActivity
import com.iremdogan.fooddeliveryproject.utils.Resource
import com.iremdogan.fooddeliveryproject.utils.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var _binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()

    private var cartAdapter = CartRecyclerViewAdapter()
    private var cartList: MutableList<MealInfo> = mutableListOf()

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
        viewModel.getCart().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING ->{

                }
                Resource.Status.SUCCESS ->{
                    cartList.addAll(it.data!!.responseBody.mealInfoList)
                    cartAdapter.setData(it.data.responseBody.mealInfoList)
                    _binding.totalTextView.text = updateTotal()
                }
                Resource.Status.ERROR ->{

                }
            }
        })
        _binding.cartRecyclerView.adapter = cartAdapter

        _binding.totalTextView.text = updateTotal()
    }

    private fun initializeListeners() {
        _binding.cartPaymentButton.setOnClickListener {
            viewModel.createOrder().observe(viewLifecycleOwner, {
                when(it.status){
                    Resource.Status.LOADING -> {
                        Log.e(CartFragment::class.java.name, "LOADING")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.e(CartFragment::class.java.name, "SUCCESS")
                        findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(CartFragment::class.java.name, it.message.toString())
                    }
                }
            })
        }

        cartAdapter.addListener(object : ICartOnClick{
            override fun onClickIncreaseButton(meal: MealInfo) {
                viewModel.addToCart(meal.mealInfo.id, 1).observe(viewLifecycleOwner, {
                    when(it.status){
                        Resource.Status.LOADING -> {
                            Log.e(CartFragment::class.java.name, "LOADING")
                        }
                        Resource.Status.SUCCESS -> {
                            Log.e(CartFragment::class.java.name, "SUCCESS")
                            _binding.totalTextView.text = updateTotal()
                            (activity as MainActivity).increaseCartCount(1)
                        }
                        Resource.Status.ERROR -> {
                            Log.e(CartFragment::class.java.name, it.message.toString())
                        }
                    }
                })

            }

            override fun onClickDecreaseButton(meal: MealInfo) {
                viewModel.removeItemFromCart(meal.mealInfo.id, 1).observe(viewLifecycleOwner, {
                    when(it.status){
                        Resource.Status.LOADING -> {
                            Log.e(CartFragment::class.java.name, "LOADING")
                        }
                        Resource.Status.SUCCESS -> {
                            Log.e(CartFragment::class.java.name, "SUCCESS")
                            _binding.totalTextView.text = updateTotal()
                            (activity as MainActivity).decreaseCartCount(1)
                        }
                        Resource.Status.ERROR -> {
                            Log.e(CartFragment::class.java.name, it.message.toString())
                        }
                    }
                })
            }

        })

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeItemFromCart(cartList[viewHolder.adapterPosition].mealInfo.id, cartList[viewHolder.adapterPosition].count.toLong()).observe(viewLifecycleOwner, {
                    when(it.status){
                        Resource.Status.LOADING -> {
                            Log.e(CartFragment::class.java.name, "LOADING")
                        }
                        Resource.Status.SUCCESS -> {
                            Log.e(CartFragment::class.java.name, "SUCCESS")
                            (activity as MainActivity).decreaseCartCount(cartList[viewHolder.adapterPosition].count)
                            cartAdapter.removeAt(viewHolder.adapterPosition)
                            _binding.totalTextView.text = updateTotal()
                        }
                        Resource.Status.ERROR -> {
                            Log.e(CartFragment::class.java.name, it.message.toString())
                        }
                    }
                })
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(_binding.cartRecyclerView)
    }

    private fun updateTotal() : String{
        var total = 0L
        cartList.forEach {
            total += it.count * it.mealInfo.price
        }
        return "$total TL"
    }

}