package com.manoj.meals.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.manoj.meals.data.dataSource.api.ApiDataWrapper
import com.manoj.meals.domain.dataModel.MealsCategory
import com.manoj.meals.domain.dataModel.MealsCategoryResponse
import com.manoj.meals.domain.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MealsDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    mealsRepository: MealsRepository
) : ViewModel() {

    private var _mMealsState: MutableStateFlow<MealsCategory?> =
        MutableStateFlow(null)

    val mealsState: StateFlow<MealsCategory?>
        get() = _mMealsState.asStateFlow()

    init {
        val mealCategoryId = savedStateHandle.get<String>("mealCategoryId") ?: ""
        _mMealsState.value = mealsRepository.getMealsCategory(mealCategoryId)
    }


}