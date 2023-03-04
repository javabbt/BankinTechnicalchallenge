package com.bankin.challenge.feature.album.domain.usecase

import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.base.domain.result.mapSuccess
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.repository.AlbumRepository

internal class GetCategoriesListUseCase(
    private val albumRepository: AlbumRepository,
) {

    suspend operator fun invoke(query: String?): Result<List<CategoryUiModel>> {
        val result = albumRepository
            .getCategories()
            .mapSuccess {
                this
            }

        return result
    }
}
