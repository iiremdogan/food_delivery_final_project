package com.iremdogan.fooddeliveryproject.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentOnboardingBinding

class OnBoardingFragment : Fragment() {

    private lateinit var _binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeButtons()
        initializeViewPager()
    }


    private fun initializeButtons() {
        _binding.skipTextView.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }

        _binding.createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_registerFragment)
        }

        _binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

    private fun initializeViewPager() {
        val viewpagerAdapter = SliderPagerAdapter(requireContext(), getOnboardingItems())
        _binding.viewPager.adapter = viewpagerAdapter
        _binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                viewpagerAdapter.setIndicatorBackground(position)
            }
        })
    }

    private fun getOnboardingItems(): List<Pair<String, Int>> {
        val onboardingItems = mutableListOf<Pair<String, Int>>()
        return onboardingItems.apply {
            add(
                Pair(
                    resources.getString(R.string.onboarding_text_first),
                    R.drawable.onboarding_first
                )
            )
            add(
                Pair(
                    resources.getString(R.string.onboarding_text_second),
                    R.drawable.onboarding_second
                )
            )
            add(
                Pair(
                    resources.getString(R.string.onboarding_text_third),
                    R.drawable.onboarding_third
                )
            )
        }
    }
}