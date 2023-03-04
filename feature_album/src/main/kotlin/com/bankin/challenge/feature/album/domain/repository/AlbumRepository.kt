package com.bankin.challenge.feature.album.domain.repository

import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel

internal interface AlbumRepository {
    suspend fun getCategories(): Result<List<CategoryUiModel>>
}
