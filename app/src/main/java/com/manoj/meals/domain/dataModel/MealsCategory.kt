package com.manoj.meals.domain.dataModel

import com.google.gson.annotations.SerializedName

data class MealsCategory(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strCategoryDescription")
    val description: String,
    @SerializedName("strCategoryThumb")
    val imageUrl: String
) {
    override fun toString(): String {
        return "Category(id='$id', category='$category', description='$description', imageUrl='$imageUrl')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MealsCategory

        if (id != other.id) return false
        if (category != other.category) return false
        if (description != other.description) return false
        return imageUrl == other.imageUrl
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageUrl.hashCode()
        return result
    }
}