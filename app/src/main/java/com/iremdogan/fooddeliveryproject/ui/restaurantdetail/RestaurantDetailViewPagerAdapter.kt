package com.iremdogan.fooddeliveryproject.ui.restaurantdetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.info.RestaurantInformationFragment
import com.iremdogan.fooddeliveryproject.ui.restaurantdetail.menu.RestaurantMenuFragment

private const val FRAGMENT_COUNT = 2

//TODO : restaurant model will be added both adapter and fragments
class RestaurantDetailViewPagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RestaurantInformationFragment()
            1 -> RestaurantMenuFragment()
            else -> RestaurantInformationFragment()
        }
    }
}