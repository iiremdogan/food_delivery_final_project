package com.iremdogan.fooddeliveryproject.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.jwt.JWT
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentSplashBinding
import com.iremdogan.fooddeliveryproject.model.local.SharedPrefManager
import com.iremdogan.fooddeliveryproject.ui.MainActivity

class SplashFragment : Fragment() {

    private lateinit var _binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAnimation()
    }

    private fun initializeAnimation() {
        _binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                val token = getToken()

                if(!isOnboardingSeen())
                    findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                else {
                    if(token.isNullOrEmpty())
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    else {
                        val tokenWithoutTag = token.split(" ")
                        val jwt = JWT(tokenWithoutTag[1])

                        if (jwt.isExpired(0))
                            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        else{
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                }

            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }

    private fun getToken(): String? {
        return SharedPrefManager(requireContext()).getToken()
    }

    private fun isOnboardingSeen(): Boolean {
        return SharedPrefManager(requireContext()).isOnboardingSeen()
    }

}