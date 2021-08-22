package com.iremdogan.fooddeliveryproject.ui.restaurantdetail.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.databinding.FragmentRestaurantMenuBinding
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealData
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.RestaurantDetailFragmentDirections
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.RestaurantDetailViewModel
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantMenuFragment(val restaurant: RestaurantData): Fragment() {

    private lateinit var _binding : FragmentRestaurantMenuBinding
    private var mealMenuAdapter = RestaurantMenuRecyclerViewAdapter()
    private val viewModel : RestaurantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantMenuBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.restaurantMenuRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        initializeViews()
        initializeListeners()
    }

    private fun initializeViews() {
        viewModel.getRestaurantMeals(restaurant.id).observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {}
                Resource.Status.SUCCESS -> {
                    mealMenuAdapter.setData(it.data?.mealData)
                }
                Resource.Status.ERROR -> {}
            }
        })
        _binding.restaurantMenuRecyclerView.adapter = mealMenuAdapter
    }

    private fun initializeListeners() {
        mealMenuAdapter.addListener(object: IMealOnClick {
            override fun onClick(meal: MealData) {
                findNavController().navigate(RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMealDetailFragment(meal.id))
            }

        })

    }
}