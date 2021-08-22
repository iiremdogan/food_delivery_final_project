package com.iremdogan.fooddeliveryproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentLoginBinding
import com.iremdogan.fooddeliveryproject.ui.MainActivity
import com.iremdogan.fooddeliveryproject.ui.register.RegisterFragment
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeButtons()
    }

    private fun initializeButtons() {
        _binding.loginButton.setOnClickListener {
            viewModel.login(_binding.usernameEditText.text.toString(), _binding.passwordEditText.text.toString())
                .observe(viewLifecycleOwner, {
                    when(it.status){
                        Resource.Status.LOADING -> {
                            Log.e(LoginFragment::class.java.name, "LOADING")
                        }
                        Resource.Status.SUCCESS -> {
                            Log.e(LoginFragment::class.java.name, "SUCCESS")
                            val i = Intent(context, MainActivity::class.java)
                            startActivity(i)
                            requireActivity().finish()
                        }
                        Resource.Status.ERROR -> {
                            Log.e(LoginFragment::class.java.name,  it.message.toString())
                            Log.e(LoginFragment::class.java.name,  it.data?.reason.toString())
                        }
                    }
                })
        }
        _binding.createAccountTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}