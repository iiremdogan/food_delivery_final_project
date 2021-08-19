package com.iremdogan.fooddeliveryproject.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentRestaurantDetailBinding

class RestaurantDetailFragment: Fragment() {

    private lateinit var _binding : FragmentRestaurantDetailBinding
    private lateinit var viewPagerAdapter : RestaurantDetailViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewPagerAdapter = RestaurantDetailViewPagerAdapter(requireActivity())

        _binding.restaurantDetailViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(_binding.restaurantDetailTabLayout, _binding.restaurantDetailViewPager){ tab, position ->
            if(position == 0)
                tab.text = "Info"
            if(position == 1)
                tab.text = "Menu"
        }.attach()
    }

    private fun initListeners() {
        _binding.restaurantDetailBackImageView.setOnClickListener { 
            findNavController().navigate(R.id.action_restaurantDetailFragment_to_homeFragment)
        }

    }
}