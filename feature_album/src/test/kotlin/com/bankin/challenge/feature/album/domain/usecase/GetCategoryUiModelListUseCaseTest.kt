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

    private val mockAlbumRepository: AlbumRepositoryImpl = mockk()

    private val cut = GetCategoriesListUseCase(mockAlbumRepository)

    @Test
    fun `return list of albums`() {
        // given
        val albums = listOf(DomainFixtures.getCategory(), DomainFixtures.getCategory())
        coEvery { mockAlbumRepository.getCategories() } returns Result.Success(albums)

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo Result.Success(albums)
    }

    @Test
    fun `WHEN onEnter is called with no value then the default query search term is null`() = runBlocking {
        // given
        val albums = listOf(DomainFixtures.getCategory(), DomainFixtures.getCategory())
        coEvery { mockAlbumRepository.getCategories() } returns Result.Success(albums)

        cut(null)

        coVerify { mockAlbumRepository.getCategories() }
    }

    @Test
    fun `filter albums with default image`() {
        // given
        val albumWithImage = DomainFixtures.getCategory()
        val albumWithoutImage = DomainFixtures.getCategory()
        val albums = listOf(albumWithImage, albumWithoutImage)
        coEvery { mockAlbumRepository.getCategories() } returns Result.Success(albums)

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo Result.Success(listOf(albumWithImage))
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val resultFailure = mockk<Result.Failure>()
        coEvery { mockAlbumRepository.getCategories() } returns resultFailure

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo resultFailure
    }
}
