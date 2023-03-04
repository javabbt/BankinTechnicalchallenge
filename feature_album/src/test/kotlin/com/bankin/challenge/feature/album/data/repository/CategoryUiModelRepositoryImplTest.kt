package com.bankin.challenge.feature.album.data.repository

import com.bankin.challenge.base.data.retrofit.ApiResult
import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.feature.album.data.DataFixtures
import com.bankin.challenge.feature.album.data.datasource.api.model.toDomainModel
import com.bankin.challenge.feature.album.data.datasource.api.response.SearchCategoryResponse
import com.bankin.challenge.feature.album.data.datasource.api.service.CategoryRetrofitService
import com.bankin.challenge.feature.album.data.datasource.database.CategoryDao
import com.bankin.challenge.feature.album.data.datasource.database.model.toDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class CategoryUiModelRepositoryImplTest {

    private val mockService: CategoryRetrofitService = mockk()

    private val mockCategoryDao: CategoryDao = mockk(relaxed = true)

    private val cut = AlbumRepositoryImpl(mockService, mockCategoryDao)

    @Test
    fun `searchAlbum handles api success and returns albums`() {
        // given
        val albums = DataFixtures.getCategoriesResultsApiModel()

        coEvery { mockService.getCategoriesAsync() } returns ApiResult.Success(
            DataFixtures.ApiResponse.getCategories(),
        )

        // when
        val actual = runBlocking { cut.getCategories() }

        // then
        val albumsDomain = albums.map { it.toDomainModel() }
        actual shouldBeEqualTo Result.Success(albumsDomain)
    }

    @Test
    fun `searchAlbum handles api success and saves album in database`() {
        // given
        val phrase = "phrase"
        coEvery { mockService.getCategoriesAsync() } returns ApiResult.Success(
            DataFixtures.ApiResponse.getCategories(),
        )

        // when
        runBlocking { cut.getCategories() }

        // then
        coVerify { mockCategoryDao.insertCategories(any()) }
    }

    @Test
    fun `searchAlbum handles api exception and fallbacks to database`() {
        // given
        val albumEntities = DataFixtures.getCategoriesEntityModels()
        val albums = albumEntities.map { it.toDomainModel() }

        coEvery { mockService.getCategoriesAsync() } returns ApiResult.Exception(UnknownHostException())
        coEvery { mockCategoryDao.getAll() } returns albumEntities

        // when
        val actual = runBlocking { cut.getCategories() }

        // then
        actual shouldBeEqualTo Result.Success(albums)
    }

    @Test
    fun `searchAlbum handles api error `() {
        // given
        val phrase = "phrase"

        coEvery { mockService.getCategoriesAsync() } returns mockk<ApiResult.Error<SearchCategoryResponse>>()

        // when
        val actual = runBlocking { cut.getCategories() }

        // then
        actual shouldBeEqualTo Result.Failure()
    }

}
