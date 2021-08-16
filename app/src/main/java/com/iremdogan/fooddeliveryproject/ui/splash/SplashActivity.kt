package com.iremdogan.fooddeliveryproject.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iremdogan.fooddeliveryproject.databinding.ActivitySplashBinding
import com.iremdogan.fooddeliveryproject.ui.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var _binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        initializeAnimation()
        setContentView(_binding.root)
    }

    private fun initializeAnimation(){
        _binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                val i = Intent(applicationContext, OnboardingActivity::class.java)
                startActivity(i)
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }

}