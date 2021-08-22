package com.iremdogan.fooddeliveryproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentHomeBinding
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData
import com.iremdogan.fooddeliveryproject.ui.home.menu.ICuisineMenuItemOnClick
import com.iremdogan.fooddeliveryproject.ui.home.menu.CuisineMenuRecyclerViewAdapter
import com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu.IRestaurantOnClick
import com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu.RestaurantRecyclerViewAdapter
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private var cuisineList: MutableList<String> = mutableListOf()
    private var cuisineMenuAdapter = CuisineMenuRecyclerViewAdapter()
    private var restaurantMenuAdapter = RestaurantRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE

        _binding.cuisineRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        initializeViews()
        initializeListeners()
    }

    private fun initializeViews() {
        viewModel.getRestaurants().observe(viewLifecycleOwner, {
            when (it.status){
                Resource.Status.LOADING -> {
                    Log.e(HomeFragment::class.java.name,"LOADING")
                }
                Resource.Status.SUCCESS -> {
                    Log.e(HomeFragment::class.java.name,"SUCCESS")

                    restaurantMenuAdapter.setData(it.data?.restaurantData)
                }
                Resource.Status.ERROR -> {
                    Log.e(HomeFragment::class.java.name,it.message.toString())
                    Log.e(HomeFragment::class.java.name, it.data?.reason.toString())
                }
            }
        })

        val cuisines = resources.getStringArray(R.array.Cuisines)
        for( i in cuisines.indices){
            cuisineList.add(cuisines[i])
        }

        _binding.cuisineRecyclerView.adapter = cuisineMenuAdapter
        cuisineMenuAdapter.setData(cuisineList)

        _binding.restaurantRecyclerView.adapter = restaurantMenuAdapter
    }

    private fun initializeListeners() {
        cuisineMenuAdapter.addListener(object : ICuisineMenuItemOnClick {
            override fun onClick(item: String) {
                viewModel.getRestaurantsByCuisine(item).observe(viewLifecycleOwner, {
                    when (it.status){
                        Resource.Status.LOADING -> {
                            Log.e(HomeFragment::class.java.name,  "LOADING")
                        }
                        Resource.Status.SUCCESS -> {
                            Log.e(HomeFragment::class.java.name, "SUCCESS")
                            restaurantMenuAdapter.setData(it.data?.restaurantData)
                        }
                        Resource.Status.ERROR -> {
                            Log.e(HomeFragment::class.java.name,  it.message.toString())
                            Log.e(HomeFragment::class.java.name,  it.data!!.reason)
                        }
                    }
                })
            }
        })

        restaurantMenuAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: RestaurantData) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(restaurant.id))
            }
        })

        _binding.viewAllRestaurantsTextView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

    }
}