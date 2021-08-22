package com.iremdogan.fooddeliveryproject.ui.mealdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremdogan.fooddeliveryproject.model.ApiRepository
import com.iremdogan.fooddeliveryproject.model.entity.meal.MealResponse
import com.iremdogan.fooddeliveryproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getMealDetails(id: Long): LiveData<Resource<MealResponse>> = apiRepository.getMealDetail(id)

}