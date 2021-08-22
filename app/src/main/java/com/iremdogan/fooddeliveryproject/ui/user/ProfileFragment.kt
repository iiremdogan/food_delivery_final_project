package com.iremdogan.fooddeliveryproject.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentProfileBinding
import com.iremdogan.fooddeliveryproject.model.entity.order.OrderData
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
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE

        _binding.profileOrdersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        initializeViews()
        initializeListeners()

    }

    private fun initializeViews() {
        viewModel.getUserInfo().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.e(ProfileFragment::class.java.name,"LOADING")
                }
                Resource.Status.SUCCESS -> {
                    Log.e(ProfileFragment::class.java.name,"SUCCESS")
                    _binding.profileNameTextView.text = it.data?.userData?.username
                    _binding.profileAddressTextView.text = it.data?.userData?.address
                    _binding.profilePhoneTextView.text = it.data?.userData?.phone
                    orderAdapter.setData(it.data!!.userData.lastOrders)
                }
                Resource.Status.ERROR -> {
                    Log.e(ProfileFragment::class.java.name,it.message.toString())
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