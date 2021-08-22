package com.iremdogan.fooddeliveryproject.ui.restaurantdetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iremdogan.fooddeliveryproject.model.entity.restaurant.RestaurantData
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.info.RestaurantInformationFragment
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.menu.RestaurantMenuFragment

private const val FRAGMENT_COUNT = 2

class RestaurantDetailViewPagerAdapter(fragment: FragmentActivity, val restaurant: RestaurantData) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RestaurantInformationFragment(restaurant)
            1 -> RestaurantMenuFragment(restaurant)
            else -> RestaurantInformationFragment(restaurant)
        }
    }
}