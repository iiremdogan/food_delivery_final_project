package com.iremdogan.fooddeliveryproject.ui.cart

import android.os.Bundle
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
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData
import com.iremdogan.fooddeliveryproject.utils.Resource
import com.iremdogan.fooddeliveryproject.utils.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var _binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()

    private var cartAdapter = CartRecyclerViewAdapter()
    private var cartList: MutableList<MealData> = mutableListOf()

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
                    cartAdapter.setData(it.data!!.cartData.mealInfoList)
                }
                Resource.Status.ERROR ->{

                }
            }
        })
        _binding.cartRecyclerView.adapter = cartAdapter

        _binding.totalTextView.text = updateTotal().toString() + " TL"
    }

    private fun initializeListeners() {
        _binding.cartPaymentButton.setOnClickListener {
            viewModel.createOrder().observe(viewLifecycleOwner, {
                when(it.status){
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
                    }
                    Resource.Status.ERROR -> {

                    }
                }
            })
        }

        cartAdapter.addListener(object : ICartOnClick{
            override fun onClickIncreaseButton(meal: MealData) {
                viewModel.increaseCartItemCount()
                _binding.totalTextView.text = updateTotal().toString() + " TL"
            }

            override fun onClickDecreaseButton(meal: MealData) {
                viewModel.decreaseCartItemCount()
                _binding.totalTextView.text = updateTotal().toString() + " TL"
            }

        })

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.decreaseCartItemCount()
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
            //total += it.count * it.price.toInt()
        }
        return total
    }

}

//        cartList.add(CartItem("", "Restaurant Name","Meal Name", "48", 1))
//        cartList.add(CartItem("", "Restaurant Name","Meal Name", "8", 2))
//        cartList.add(CartItem("", "Restaurant Name","Meal Name", "1", 1))
//        cartList.add(CartItem("", "Restaurant Name","Meal Name", "122", 1))