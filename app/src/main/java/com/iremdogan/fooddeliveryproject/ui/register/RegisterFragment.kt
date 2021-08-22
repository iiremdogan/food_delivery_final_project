package com.iremdogan.fooddeliveryproject.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentRegisterBinding
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeButtons()
    }

    private fun initializeButtons() {
        _binding.createAccountButton.setOnClickListener {
            viewModel.register(
                _binding.nameEditText.text.toString(),
                _binding.surnameEditText.text.toString(),
                _binding.usernameEditText.text.toString(),
                _binding.emailEditText.text.toString(),
                _binding.passwordEditText.text.toString())
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            Log.e(RegisterFragment::class.java.name, "LOADING")
                        }
                        Resource.Status.SUCCESS -> {
                            Log.e(RegisterFragment::class.java.name, "SUCCESS")
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        }
                        Resource.Status.ERROR -> {
                            Log.e(RegisterFragment::class.java.name, it.message.toString())
                            Log.e(RegisterFragment::class.java.name, it.data?.reason.toString())
                        }
                    }
                })
        }
        _binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}