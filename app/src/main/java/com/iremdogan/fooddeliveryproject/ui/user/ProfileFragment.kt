package com.iremdogan.fooddeliveryproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentProfileBinding
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var _binding : FragmentProfileBinding
    private val viewModel: UserViewModel by viewModels()
    private var orderAdapter = LastOrderRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.profileOrdersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        initializeViews()
        initializeListeners()

    }

    private fun initializeViews() {
        viewModel.getUserInfo().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    _binding.profileNameTextView.text = it.data?.userData?.username
                    _binding.profileAddressTextView.text = it.data?.userData?.address
                    _binding.profilePhoneTextView.text = it.data?.userData?.phone
                    orderAdapter.setData(it.data!!.userData.lastOrders)
                }
                Resource.Status.ERROR -> {

                }
            }
        })

        _binding.profileOrdersRecyclerView.adapter = orderAdapter
    }

    private fun initializeListeners() {
        _binding.settingsImageView.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }
    }

}