package com.iremdogan.fooddeliveryproject.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.*
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentMealDetailBinding

class MealDetailFragment: Fragment() {

    private lateinit var _binding: FragmentMealDetailBinding
    private lateinit var layoutManager: FlexboxLayoutManager
    private var ingredientAdapter = IngredientRecyclerViewAdapter()
    private var ingredientList: MutableList<IngredientModel> = mutableListOf()
    private var count: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        addListeners()
    }

    private fun initializeViews() {
        ingredientList.add(IngredientModel("Tomato", true))
        ingredientList.add(IngredientModel("Pepperoni", true))
        ingredientList.add(IngredientModel("Cheese", true))
        ingredientList.add(IngredientModel("Mushroom", true))
        ingredientList.add(IngredientModel("Special Sauce", true))
        ingredientList.add(IngredientModel("Sausage", true))

        layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.alignItems = AlignItems.FLEX_START

        _binding.mealIngredientsRecyclerView.layoutManager = layoutManager
        _binding.mealIngredientsRecyclerView.adapter = ingredientAdapter
        ingredientAdapter.setData(ingredientList)

        _binding.itemCountTextView.text = count.toString()
    }

    private fun addListeners() {
        ingredientAdapter.addListener(object : IIngredientOnClick{
            override fun onClick(ingredient: IngredientModel) {
                ingredient.isIncluded = !ingredient.isIncluded
            }

        })

        _binding.mealDetailBackImageView.setOnClickListener {
            findNavController().navigate(R.id.action_mealDetailFragment_to_restaurantDetailFragment)
        }

        _binding.increaseCountButton.setOnClickListener {
            count++
            _binding.itemCountTextView.text = count.toString()
        }

        _binding.decreaseCountButton.setOnClickListener {
            if(count - 1 != 0){
                count--
                _binding.itemCountTextView.text = count.toString()
            }
        }

        _binding.addCartButton.setOnClickListener {
            //TODO : add cart (increase cart badge on bottom navigation)
            findNavController().navigate(R.id.action_mealDetailFragment_to_restaurantDetailFragment)
        }
    }

    private fun getMealIngredients() : MutableList<IngredientModel>{
        val includedIngredientsList : MutableList<IngredientModel> = mutableListOf()

        ingredientList.forEach {
            if(it.isIncluded)
                includedIngredientsList.add(it)
        }
        return includedIngredientsList
    }

}