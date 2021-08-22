package com.iremdogan.fooddeliveryproject.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentSettingsBinding
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var _binding : FragmentSettingsBinding
    private val viewModel : SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeListeners()
    }

    private fun initializeViews() {
        viewModel.getUserInfo().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    _binding.settingsNameLayout.editText?.setText(it.data?.userData?.name)
                    _binding.settingsSurnameLayout.editText?.setText(it.data?.userData?.surname)
                    _binding.settingsUserEmailLayout.editText?.setText(it.data?.userData?.email)
                    _binding.settingsUserNameLayout.editText?.setText(it.data?.userData?.username)
                    _binding.settingsUserPasswordLayout.editText?.setText(it.data?.userData?.password)
                    _binding.settingsUserAddressLayout.editText?.setText(it.data?.userData?.address)
                    _binding.settingsUserPhoneLayout.editText?.setText(it.data?.userData?.phone)
                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }

    private fun initializeListeners() {
        _binding.settingsBackImageView.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.settingsUpdateButton.setOnClickListener {
            viewModel.updateUserDetails(
                _binding.settingsNameLayout.editText?.text.toString(),
                _binding.settingsSurnameLayout.editText?.text.toString(),
                _binding.settingsUserEmailLayout.editText?.text.toString(),
                _binding.settingsUserNameLayout.editText?.text.toString(),
                _binding.settingsUserPasswordLayout.editText?.text.toString(),
                _binding.settingsUserAddressLayout.editText?.text.toString(),
                _binding.settingsUserPhoneLayout.editText?.text.toString(),
            ) .observe(viewLifecycleOwner, {
                when(it.status){
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        findNavController().navigate(R.id.action_settingsFragment_to_profileFragment)
                    }
                    Resource.Status.ERROR -> {

                    }
                }
            })
        }

    }

}