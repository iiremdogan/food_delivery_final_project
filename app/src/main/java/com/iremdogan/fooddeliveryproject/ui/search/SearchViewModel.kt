package com.iremdogan.fooddeliveryproject.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getRestaurants() = apiRepository.getRestaurants()

}