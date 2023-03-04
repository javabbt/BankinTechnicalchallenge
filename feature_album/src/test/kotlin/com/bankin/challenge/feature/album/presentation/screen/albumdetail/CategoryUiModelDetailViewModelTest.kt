package com.bankin.challenge.feature.album.presentation.screen.albumdetail

import com.bankin.challenge.library.testutils.CoroutinesTestDispatcherExtension
import com.bankin.challenge.library.testutils.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class CategoryUiModelDetailViewModelTest {

//    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()
//
//    private val cut = SubCategoriesViewModel(
//        mockGetAlbumUseCase,
//    )
//
//    @Test
//    @Disabled("mockk can't correctly mock this function https://github.com/mockk/mockk/issues/957")
//    fun `onEnter album is not found`() = runTest {
//        // given
//        val albumName = "Thriller"
//        val artistName = "Michael Jackson"
//        val mbId = "123"
//
//        val mockAlbumDetailFragmentArgs = AlbumDetailFragmentArgs(albumName, artistName, mbId)
//
//        // mockk Bug:
//        // Exception in thread "Test worker @coroutine#4" io.mockk.MockKException: no answer found for:
//        // GetAlbumUseCase(#1).execute(Thriller, Michael Jackson, 123, continuation {})
//        coEvery {
//            mockGetAlbumUseCase(artistName, albumName, mbId)
//        } returns Result.Failure()
//
//        // when
//        cut.onEnter(mockAlbumDetailFragmentArgs)
//
//        // then
//        cut.uiStateFlow.value shouldBeEqualTo UiState.Error
//    }
}
