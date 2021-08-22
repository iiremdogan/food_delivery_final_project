package com.iremdogan.fooddeliveryproject.ui.restaurantdetail.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iremdogan.fooddeliveryproject.databinding.FragmentRestaurantInformationBinding
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantInformationFragment(val restaurant: RestaurantData): Fragment() {

    private lateinit var _binding : FragmentRestaurantInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantInformationBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.restaurantInfoTextView.text = restaurant.information
        _binding.restaurantMinDeliveryTimeTextView.text = restaurant.minDeliveryTime
        _binding.restaurantMinFeeTextView.text = restaurant.minDeliveryFee
    }

}