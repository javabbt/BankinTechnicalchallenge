package com.bankin.challenge.feature.album.domain.usecase

import com.bankin.challenge.feature.album.data.repository.AlbumRepositoryImpl
import io.mockk.mockk

class GetCategoryUiModelUseCaseTest {

    private val mockAlbumRepository: AlbumRepositoryImpl = mockk()



//    @Test
//    fun `return album`() {
//        // given
//        val albumName = "Thriller"
//        val artistName = "Michael Jackson"
//        val mbId = "123"
//
//        val categoryUiModel = mockk<CategoryUiModel>()
//        coEvery { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) } answers { Result.Success(categoryUiModel) }
//
//        // when
//        val actual = runBlocking { cut(artistName, albumName, mbId) }
//
//        // then
//        actual shouldBeEqualTo Result.Success(categoryUiModel)
//    }
//
//    @Test
//    fun `return error`() {
//        // given
//        val albumName = "Thriller"
//        val artistName = "Michael Jackson"
//        val mbId = "123"
//        val resultFailure = mockk<Result.Failure>()
//
//        coEvery { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) } returns
//            resultFailure
//
//        // when
//        val actual = runBlocking { cut(artistName, albumName, mbId) }
//
//        // then
//        coVerify { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) }
//        actual shouldBeEqualTo resultFailure
//    }
}
