package com.manoj.meals.domain.repository

import com.manoj.meals.data.dataSource.api.ApiDataWrapper
import com.manoj.meals.domain.dataModel.MealsCategory
import com.manoj.meals.domain.dataModel.MealsCategoryResponse

interface MealsRepository {
    suspend fun getMealsCategory(): ApiDataWrapper<MealsCategoryResponse>

    fun getMealsCategory(id: String): MealsCategory?
}