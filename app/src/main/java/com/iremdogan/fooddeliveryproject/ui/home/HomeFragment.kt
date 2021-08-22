package com.iremdogan.fooddeliveryproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentHomeBinding
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData
import com.iremdogan.fooddeliveryproject.ui.home.menu.CuisineMenuItemModel
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

    private var cuisineMenuAdapter = CuisineMenuRecyclerViewAdapter()
    private var cuisineList: MutableList<CuisineMenuItemModel> = mutableListOf()
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

                }
                Resource.Status.SUCCESS -> {
                    restaurantMenuAdapter.setData(it.data?.restaurantData)
                }
                Resource.Status.ERROR -> {

                }
            }
        })

        _binding.cuisineRecyclerView.adapter = cuisineMenuAdapter
        cuisineMenuAdapter.setData(cuisineList)

        _binding.restaurantRecyclerView.adapter = restaurantMenuAdapter
    }

    private fun initializeListeners() {
        cuisineMenuAdapter.addListener(object : ICuisineMenuItemOnClick {
            override fun onClick(itemModel: CuisineMenuItemModel) {
                viewModel.getRestaurantsByCuisine(itemModel.text).observe(viewLifecycleOwner, {
                    when (it.status){
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {
                            restaurantMenuAdapter.setData(it.data?.restaurantData)
                        }
                        Resource.Status.ERROR -> {

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
//        cuisineList.add(CuisineMenuItemModel("test-0", ""))
//        cuisineList.add(CuisineMenuItemModel("test-1", ""))
//        cuisineList.add(CuisineMenuItemModel("test-2", ""))
//        cuisineList.add(CuisineMenuItemModel("test-3", ""))
//        cuisineList.add(CuisineMenuItemModel("test-4", ""))
//        cuisineList.add(CuisineMenuItemModel("test-5", ""))
//        cuisineList.add(CuisineMenuItemModel("test-6", ""))
//        cuisineList.add(CuisineMenuItemModel("test-7", ""))
//        cuisineList.add(CuisineMenuItemModel("test-8", ""))
//        cuisineList.add(CuisineMenuItemModel("test-9", ""))
//
//        restaurantList.add(RestaurantMenuModel("", "test-0", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-1", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-2", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-3", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-4", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-5", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-6", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-7", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-8", "10dk", "20TL"))
//        restaurantList.add(RestaurantMenuModel("", "test-9", "10dk", "20TL"))