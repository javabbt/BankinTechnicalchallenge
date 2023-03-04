package com.bankin.challenge.feature.album.domain.usecase

import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.repository.AlbumRepository

internal class GetSubCategoriesUseCase(
    private val albumRepository: AlbumRepository,
) {
    suspend operator fun invoke(id: Int): Result<List<CategoryUiModel>?> {
        return albumRepository
            .getSubCategories(id)
    }
}