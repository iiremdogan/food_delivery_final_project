package com.iremdogan.fooddeliveryproject.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.databinding.FragmentSearchBinding
import com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu.RestaurantMenuModel
import com.iremdogan.fooddeliveryproject.ui.home.restaurantmenu.RestaurantRecyclerViewAdapter

class SearchFragment : Fragment() {

    private lateinit var _binding : FragmentSearchBinding
    private var restaurantMenuAdapter = RestaurantRecyclerViewAdapter()
    private var restaurantList: MutableList<RestaurantMenuModel> = mutableListOf()

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
        restaurantList.add(RestaurantMenuModel("", "test-0", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-1", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-2", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-3", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-4", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-5", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-6", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-7", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-8", "10dk", "20TL"))
        restaurantList.add(RestaurantMenuModel("", "test-9", "10dk", "20TL"))

        _binding.searchRecyclerView.adapter = restaurantMenuAdapter
        restaurantMenuAdapter.setData(restaurantList)
    }

    private fun addListeners() {
        _binding.searchView.setOnClickListener {
            _binding.searchView.setIconifiedByDefault(false)
        }

        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = searchTextOnRestaurantList(query)
                setRestaurants(filterList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterList = searchTextOnRestaurantList(newText)
                setRestaurants(filterList)
                return true
            }

        })
    }

    fun searchTextOnRestaurantList(text: String?): List<RestaurantMenuModel>? {
        if (text.isNullOrEmpty())
            return restaurantList

        val filterList: MutableList<RestaurantMenuModel> = mutableListOf()
        restaurantList?.forEach { restaurant ->
            if (restaurant.name.contains(text, true))
                filterList.add(restaurant)
        }
        return filterList
    }

    private fun setRestaurants(restaurantList: List<RestaurantMenuModel>?) {
//        isRestaurantListVisible(restaurantList.isNullOrEmpty().not())
        restaurantMenuAdapter.setData(restaurantList)
        _binding.searchRecyclerView.adapter = restaurantMenuAdapter
    }

}