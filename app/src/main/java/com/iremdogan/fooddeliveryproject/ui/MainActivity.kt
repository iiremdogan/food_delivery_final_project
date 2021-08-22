package com.iremdogan.fooddeliveryproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iremdogan.fooddeliveryproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var badge : BadgeDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navController = Navigation.findNavController(this, R.id.fragment_host)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        badge = bottomNavigationView.getOrCreateBadge(R.id.cartFragment)
        badge.number = 0
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    fun clearCartCount(){
        badge.isVisible = false
    }

    fun setCartCount(count: Int){
        badge.number = count
    }

    fun increaseCartCount(count : Int) {
        badge.number += count
    }

    fun decreaseCartCount(count : Int) {
        if(badge.number != 0)
            badge.number -= count
        if(badge.number == 0)
            badge.isVisible = false
    }
}