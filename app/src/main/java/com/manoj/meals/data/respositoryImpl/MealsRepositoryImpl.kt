package com.manoj.meals.data.respositoryImpl

import com.manoj.meals.data.dataSource.api.ApiDataWrapper
import com.manoj.meals.data.dataSource.api.MealsApi
import com.manoj.meals.data.dataSource.api.SafeApiCall
import com.manoj.meals.domain.dataModel.MealsCategory
import com.manoj.meals.domain.dataModel.MealsCategoryResponse
import com.manoj.meals.domain.repository.MealsRepository
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val api: MealsApi) : MealsRepository,
    SafeApiCall {

    private var cachedMeals = listOf<MealsCategory>()
    override suspend fun getMealsCategory(): ApiDataWrapper<MealsCategoryResponse> = callApi {
        val response = api.getMealsCategories()
        cachedMeals = response.categories
        response
    }

    override fun getMealsCategory(id: String): MealsCategory? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }
}