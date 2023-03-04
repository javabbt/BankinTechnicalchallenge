package com.bankin.challenge.feature.album.presentation.screen.categorylist

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.base.presentation.nav.NavManager
import com.bankin.challenge.base.presentation.viewmodel.BaseAction
import com.bankin.challenge.base.presentation.viewmodel.BaseState
import com.bankin.challenge.base.presentation.viewmodel.BaseViewModel
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.usecase.GetCategoriesListUseCase
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel.Action
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel.UiState.Error
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel.UiState.Loading
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class CategoriesViewModel(
    private val navManager: NavManager,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
) : BaseViewModel<UiState, Action>(Loading) {

    fun onEnter() {
        getAlbumList()
    }

    private var job: Job? = null

    private fun getAlbumList() {
        if (job != null) {
            job?.cancel()
            job = null
        }

        job = viewModelScope.launch {
            getCategoriesListUseCase().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.value.isEmpty()) {
                            Action.AlbumListLoadFailure
                        } else {
                            Action.AlbumListLoadSuccess(result.value)
                        }
                    }
                    is Result.Failure -> {
                        Action.AlbumListLoadFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    fun onAlbumClick(categoryUiModel: CategoryUiModel) {
        val navDirections =
            CategoriestFragmentDirections.actionCategoriesListToSubCategories(categoryUiModel.id)

        navManager.navigate(navDirections)
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumListLoadSuccess(private val categoryUiModels: List<CategoryUiModel>) : Action {
            override fun reduce(state: UiState) = Content(categoryUiModels)
        }

        object AlbumListLoadFailure : Action {
            override fun reduce(state: UiState) = Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(val categoryUiModels: List<CategoryUiModel>) : UiState
        object Loading : UiState
        object Error : UiState
    }
}
