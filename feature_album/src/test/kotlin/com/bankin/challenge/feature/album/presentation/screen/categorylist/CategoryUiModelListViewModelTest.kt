package com.bankin.challenge.feature.album.presentation.screen.categorylist

import androidx.lifecycle.SavedStateHandle
import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.base.presentation.nav.NavManager
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.usecase.GetCategoriesListUseCase
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel.UiState
import com.bankin.challenge.library.testutils.CoroutinesTestDispatcherExtension
import com.bankin.challenge.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class CategoryUiModelListViewModelTest {

    private val mockGetCategoriesListUseCase: GetCategoriesListUseCase = mockk()

    private val mockNavManager: NavManager = mockk(relaxed = true)

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    private val cut = CategoriesViewModel(
        savedStateHandle,
        mockNavManager,
        mockGetCategoriesListUseCase,
    )

    @Test
    fun `onEnter emits state error`() = runTest {
        // given
        coEvery { mockGetCategoriesListUseCase.invoke(null) } returns Result.Success(emptyList())

        // when
        cut.onEnter(null)

        // then
        advanceUntilIdle()

        cut.uiStateFlow.value shouldBeEqualTo UiState.Error
    }

    @Test
    fun `onEnter emits state success`() = runTest {
        // given
        val categoryUiModel = CategoryUiModel("Yann", null, 0)
        val albums = listOf(categoryUiModel)
        coEvery { mockGetCategoriesListUseCase.invoke(null) } returns Result.Success(albums)

        // when
        cut.onEnter(null)

        // then
        advanceUntilIdle()

        cut.uiStateFlow.value shouldBeEqualTo UiState.Content(
            categoryUiModels = albums,
        )
    }

    @Test
    fun `onAlbumClick navigate to album detail`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "mbId"

        val categoryUiModel = CategoryUiModel(
            name = albumName,
            id = 0
        )

        val navDirections = CategoriestFragmentDirections.actionCategoriesListToSubCategories(
            0
        )

        // when
        cut.onAlbumClick(categoryUiModel)

        // then
        coVerify { mockNavManager.navigate(navDirections) }
    }
}
