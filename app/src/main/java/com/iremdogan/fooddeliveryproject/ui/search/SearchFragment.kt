package com.iremdogan.fooddeliveryproject.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.databinding.FragmentSearchBinding
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData
import com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu.IRestaurantOnClick
import com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu.RestaurantRecyclerViewAdapter
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var _binding : FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    private var restaurantMenuAdapter = RestaurantRecyclerViewAdapter()
    private var restaurantList: MutableList<RestaurantData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.searchRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        initializeViews()
        addListeners()
    }

    private fun initializeViews() {
        viewModel.getRestaurants().observe(viewLifecycleOwner, {
            when (it.status){
                Resource.Status.LOADING -> {
                    Log.e(SearchFragment::class.java.name, "LOADING")
                }
                Resource.Status.SUCCESS -> {
                    Log.e(SearchFragment::class.java.name, "LOADING")
                    restaurantMenuAdapter.setData(it.data?.restaurantData)
                    restaurantList.addAll(it.data?.restaurantData!!)
                }
                Resource.Status.ERROR -> {
                    Log.e(SearchFragment::class.java.name, "LOADING")
                }
            }
        })
        _binding.searchRecyclerView.adapter = restaurantMenuAdapter
    }

    private fun addListeners() {
        _binding.searchView.setOnClickListener {
            _binding.searchView.setIconifiedByDefault(false)
        }

        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                restaurantMenuAdapter.setData(searchTextOnRestaurantList(query))
                _binding.searchRecyclerView.adapter = restaurantMenuAdapter
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                restaurantMenuAdapter.setData(searchTextOnRestaurantList(newText))
                _binding.searchRecyclerView.adapter = restaurantMenuAdapter
                return true
            }

        })

        restaurantMenuAdapter.addListener(object: IRestaurantOnClick{
            override fun onClick(restaurant: RestaurantData) {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToRestaurantDetailFragment(restaurant.id))
            }

        })
    }

    fun searchTextOnRestaurantList(text: String?): List<RestaurantData> {
        if (text.isNullOrEmpty())
            return restaurantList

        val filterList: MutableList<RestaurantData> = mutableListOf()
        restaurantList.forEach { restaurant ->
            if (restaurant.name.contains(text, true))
                filterList.add(restaurant)
        }
        return filterList
    }

}