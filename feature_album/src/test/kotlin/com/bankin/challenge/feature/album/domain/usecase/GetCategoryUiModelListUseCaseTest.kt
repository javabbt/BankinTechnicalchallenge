package com.bankin.challenge.feature.album.domain.usecase

import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.feature.album.data.repository.AlbumRepositoryImpl
import com.bankin.challenge.feature.album.domain.DomainFixtures
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetCategoryUiModelListUseCaseTest {

    private val mockCategoryRepository: AlbumRepositoryImpl = mockk()

    private val cut = GetCategoriesListUseCase(mockCategoryRepository)

    @Test
    fun `return list of categories`() {
        // given
        val categories = listOf(DomainFixtures.getCategory(), DomainFixtures.getCategory())
        coEvery { mockCategoryRepository.getCategories() } returns Result.Success(categories)

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo Result.Success(categories)
    }

    @Test
    fun `WHEN onEnter is called with no value then the default query search term is null`() = runBlocking {
        // given
        val albums = listOf(DomainFixtures.getCategory(), DomainFixtures.getCategory())
        coEvery { mockCategoryRepository.getCategories() } returns Result.Success(albums)

        cut(null)

        coVerify { mockCategoryRepository.getCategories() }
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val resultFailure = mockk<Result.Failure>()
        coEvery { mockCategoryRepository.getCategories() } returns resultFailure

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo resultFailure
    }
}
