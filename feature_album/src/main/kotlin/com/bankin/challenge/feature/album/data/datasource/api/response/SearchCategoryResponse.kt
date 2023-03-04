package com.bankin.challenge.feature.album.data.datasource.api.response

import com.bankin.challenge.feature.album.data.datasource.api.model.Category
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchCategoryResponse(
    val resources: List<Category>
)
