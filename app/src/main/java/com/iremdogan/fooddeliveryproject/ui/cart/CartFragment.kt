package com.iremdogan.fooddeliveryproject.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iremdogan.fooddeliveryproject.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var _binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()

    }

    private fun initializeViews() {
        TODO("Not yet implemented")
    }

}