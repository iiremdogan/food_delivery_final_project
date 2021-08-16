package com.iremdogan.fooddeliveryproject.ui.onboarding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity(), IViewPagerAdapterClickListener {

    private lateinit var _binding : ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        initializeViewPager()
        setContentView(_binding.root)
    }

    private fun initializeViewPager() {
        val  viewpagerAdapter = SliderPagerAdapter(this, getOnboardingItems(), this)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = viewpagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                viewpagerAdapter.setIndicatorBackground(position)
            }
        })
    }

    private fun getOnboardingItems(): List<Pair<String, Int>> {
        val onboardingItems = mutableListOf<Pair<String, Int>>()
        return onboardingItems.apply {
            add( Pair(resources.getString(R.string.onboarding_text_first), R.drawable.onboarding_first))
            add( Pair(resources.getString(R.string.onboarding_text_second), R.drawable.onboarding_second))
            add( Pair(resources.getString(R.string.onboarding_text_third), R.drawable.onboarding_third))
        }
    }

    override fun onCreateAccountClicked() {
        Toast.makeText(this,"create account button clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginClicked() {
        Toast.makeText(this,"login button clicked", Toast.LENGTH_SHORT).show()
    }

}