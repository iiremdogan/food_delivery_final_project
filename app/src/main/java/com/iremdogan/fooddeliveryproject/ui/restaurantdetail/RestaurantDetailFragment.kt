package com.iremdogan.fooddeliveryproject.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.iremdogan.fooddeliveryproject.databinding.FragmentRestaurantDetailBinding
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment: Fragment() {

    private lateinit var _binding : FragmentRestaurantDetailBinding
    private lateinit var viewPagerAdapter : RestaurantDetailViewPagerAdapter
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()

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
        viewModel.getRestaurantDetail(args.restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                    val restaurant = it.data?.restaurantData
                    Glide.with(_binding.restaurantImageView.context)
                        .load(restaurant!!.imageUrl).into(_binding.restaurantImageView)
                    _binding.restaurantNameTextView.text = restaurant.name
                    viewPagerAdapter = RestaurantDetailViewPagerAdapter(requireActivity(), restaurant)
                }
                Resource.Status.ERROR -> {
                }
            }
        })

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
            findNavController().popBackStack()
        }

    }
}