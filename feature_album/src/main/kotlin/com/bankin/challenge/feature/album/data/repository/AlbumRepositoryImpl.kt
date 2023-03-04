package com.bankin.challenge.feature.album.data.repository

import com.bankin.challenge.base.data.retrofit.ApiResult
import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.feature.album.data.datasource.api.model.toDomainModel
import com.bankin.challenge.feature.album.data.datasource.api.model.toEntityModel
import com.bankin.challenge.feature.album.data.datasource.api.service.CategoryRetrofitService
import com.bankin.challenge.feature.album.data.datasource.database.CategoryDao
import com.bankin.challenge.feature.album.data.datasource.database.model.toDomainModel
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.repository.AlbumRepository
import timber.log.Timber

internal class AlbumRepositoryImpl(
    private val categoryRetrofitService: CategoryRetrofitService,
    private val categoryDao: CategoryDao,
) : AlbumRepository {

    override suspend fun getCategories(): Result<List<CategoryUiModel>> =
        when (val apiResult = categoryRetrofitService.getCategoriesAsync()) {
            is ApiResult.Success -> {
                val categories = apiResult
                    .data
                    .resources
                    .also { categories ->
                        val categoriesEntityModels = categories.map { it.toEntityModel() }
                        categoryDao.insertCategories(categoriesEntityModels)
                    }
                    .map {
                        it.toDomainModel()
                    }

                Result.Success(categories)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)

                val albums = categoryDao
                    .getAll()
                    .map { it.toDomainModel() }

                Result.Success(albums)
            }
        }

    override suspend fun getSubCategories(id: Int): Result<List<CategoryUiModel>?> {
        return Result.Success(categoryDao.getSubCategories(id)?.map { it.toDomainModel()} ?: emptyList())
    }

}
