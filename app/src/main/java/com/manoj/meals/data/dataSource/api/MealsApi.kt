package com.manoj.meals.data.dataSource.api

import com.manoj.meals.domain.dataModel.MealsCategoryResponse
import retrofit2.http.GET

interface MealsApi {

    @GET("categories.php")
    suspend fun getMealsCategories(): MealsCategoryResponse
}