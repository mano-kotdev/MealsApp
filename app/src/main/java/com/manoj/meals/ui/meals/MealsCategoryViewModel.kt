package com.manoj.meals.ui.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manoj.meals.data.dataSource.api.ApiDataWrapper
import com.manoj.meals.domain.dataModel.MealsCategoryResponse
import com.manoj.meals.domain.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealsCategoryViewModel @Inject constructor(private val mealsRepository: MealsRepository) :
    ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _mealsState.value = getMeals()
        }
    }

    private var _mealsState: MutableStateFlow<ApiDataWrapper<MealsCategoryResponse>> =
        MutableStateFlow(ApiDataWrapper.Loading)
    val mealsState: StateFlow<ApiDataWrapper<MealsCategoryResponse>>
        get() = _mealsState.asStateFlow()

    private suspend fun getMeals(): ApiDataWrapper<MealsCategoryResponse> {
        return mealsRepository.getMealsCategory()
    }

}