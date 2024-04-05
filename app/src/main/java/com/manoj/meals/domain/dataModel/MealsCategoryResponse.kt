package com.manoj.meals.domain.dataModel

data class MealsCategoryResponse(
    val categories: List<MealsCategory>
) {
    override fun toString(): String {
        return "MealsCategoryResponse(categories=$categories)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MealsCategoryResponse

        return categories == other.categories
    }

    override fun hashCode(): Int {
        return categories.hashCode()
    }
}