package com.iremdogan.fooddeliveryproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremdogan.fooddeliveryproject.R
import com.iremdogan.fooddeliveryproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var _binding : FragmentProfileBinding
    private var orderAdapter = LastOrderRecyclerViewAdapter()
    private var orderList: MutableList<OrderModel> = mutableListOf()

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
        orderList.add(OrderModel("", "Restaurant Name","Meal Name", "48"))
        orderList.add(OrderModel("", "Restaurant Name","Meal Name", "8"))
        orderList.add(OrderModel("", "Restaurant Name","Meal Name", "1"))
        orderList.add(OrderModel("", "Restaurant Name","Meal Name", "122"))

        _binding.profileOrdersRecyclerView.adapter = orderAdapter
        orderAdapter.setData(orderList)
    }

    private fun initializeListeners() {
        _binding.settingsImageView.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }
    }

}