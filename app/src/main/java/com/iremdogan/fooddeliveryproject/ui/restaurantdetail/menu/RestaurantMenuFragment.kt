package com.iremdogan.fooddeliveryproject.ui.restaurantdetail.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentRestaurantMenuBinding
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.MealModel

class RestaurantMenuFragment: Fragment() {

    private lateinit var _binding : FragmentRestaurantMenuBinding
    private var mealMenuAdapter = RestaurantMenuRecyclerViewAdapter()
    private var mealList: MutableList<MealModel> = mutableListOf()

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
        mealList.add(MealModel("", "test-0", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-1", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-2", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-3", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-4", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-5", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-6", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-7", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-8", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))
        mealList.add(MealModel("", "test-9", listOf("Tomato","Mushroom","Pepperoni", "Cheese"), "20TL"))

        _binding.restaurantMenuRecyclerView.adapter = mealMenuAdapter
        mealMenuAdapter.setData(mealList)
    }

    private fun initializeListeners() {
        mealMenuAdapter.addListener(object: IMealOnClick {
            override fun onClick(meal: MealModel) {
                findNavController().navigate(R.id.action_restaurantDetailFragment_to_mealDetailFragment)
            }

        })

    }
}