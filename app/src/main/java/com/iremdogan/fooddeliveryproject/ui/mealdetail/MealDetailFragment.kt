package com.iremdogan.fooddeliveryproject.ui.mealdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.flexbox.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentMealDetailBinding
import com.iremdogan.fooddeliveryproject.ui.MainActivity
import com.iremdogan.fooddeliveryproject.ui.cart.CartViewModel
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment: Fragment() {

    private lateinit var _binding: FragmentMealDetailBinding
    private val args: MealDetailFragmentArgs by navArgs()
    private val viewModel: MealDetailViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var layoutManager: FlexboxLayoutManager
    private var ingredientAdapter = IngredientRecyclerViewAdapter()
    private var ingredientList: MutableList<Pair<String, Boolean>> = mutableListOf()
    private var count: Long = 1L

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

        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE

        initializeViews()
        addListeners()
    }

    private fun initializeViews() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner, {
            when (it.status){
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    Glide.with(requireContext()).load(it.data?.mealData?.imageUrl).into(_binding.mealImageView)
                    _binding.mealDescriptionTextView.text = it.data?.mealData?.description
                    _binding.mealNameTextView.text = it.data?.mealData?.name
                    it.data!!.mealData.ingredients.forEach { ingredient->
                        ingredientList.add(Pair(ingredient, true))
                    }
                    ingredientAdapter.setData(it.data.mealData.ingredients)
                }
                Resource.Status.ERROR -> {

                }
            }
        })

        layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.alignItems = AlignItems.FLEX_START

        _binding.mealIngredientsRecyclerView.layoutManager = layoutManager
        _binding.mealIngredientsRecyclerView.adapter = ingredientAdapter

        _binding.itemCountTextView.text = count.toString()
    }

    private fun addListeners() {
        ingredientAdapter.addListener(object : IIngredientOnClick{
            override fun onClick(ingredient: String, position: Int) {
                if(ingredientList[position].second)
                    ingredientList[position] = Pair(ingredient, false)
                else
                    ingredientList[position] = Pair(ingredient, true)
            }
        })

        _binding.mealDetailBackImageView.setOnClickListener {
            findNavController().navigate(R.id.action_mealDetailFragment_to_homeFragment)
        }

        _binding.increaseCountButton.setOnClickListener {
            count++
            _binding.itemCountTextView.text = count.toString()
        }

        _binding.decreaseCountButton.setOnClickListener {
            if(count - 1L != 0L){
                count--
                _binding.itemCountTextView.text = count.toString()
            }
        }

        _binding.addCartButton.setOnClickListener {

            cartViewModel.addToCart(args.mealId, count = count).observe(viewLifecycleOwner, {
                when (it.status){
                    Resource.Status.LOADING -> {
                        Log.e(MealDetailFragment::class.java.name, "LOADING")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.e(MealDetailFragment::class.java.name, "SUCCESS")
                        (activity as MainActivity).increaseCartCount(count.toInt())
                        findNavController().navigate(R.id.action_mealDetailFragment_to_homeFragment)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(MealDetailFragment::class.java.name, it.message.toString())
                    }
                }
            })
        }
    }
}