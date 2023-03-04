package com.bankin.challenge.feature.album.data

import com.bankin.challenge.feature.album.data.datasource.api.model.Category
import com.bankin.challenge.feature.album.data.datasource.api.model.Parent
import com.bankin.challenge.feature.album.data.datasource.api.response.SearchCategoryResponse
import com.bankin.challenge.feature.album.data.datasource.database.model.CategoryEntityModel

object DataFixtures {

    internal fun getCategoriesResultsApiModel() = listOf(
        getCategoryApiModel(),
    )

    internal fun getCategoriesEntityModels() = listOf(
        getCategoryEntityModel(),
        getCategoryEntityModel(),
    )

    internal fun getCategoryApiModel(): Category = Category(
        custom = false, id = 0, isDeleted = false, name = "Yann", other = false, parent = Parent(
            id = 0,
            resourceType = "",
            resourceUri = "",
        ), resourceType = "test", resourceUri = "test"
    )


    private fun getCategoryEntityModel(): CategoryEntityModel = CategoryEntityModel(
        custom = false, id = 0, isDeleted = false, name = "Yann", other = false,
        resourceType = "",
        resourceUri = "",
        parent = null
    )


    object ApiResponse {
        internal fun getCategories() = SearchCategoryResponse(
                resources = getCategoriesResultsApiModel(),
            )
    }
}
