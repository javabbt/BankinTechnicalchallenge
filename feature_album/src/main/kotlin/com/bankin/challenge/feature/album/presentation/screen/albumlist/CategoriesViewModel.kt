package com.bankin.challenge.feature.album.presentation.screen.albumlist

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bankin.challenge.base.domain.result.Result
import com.bankin.challenge.base.presentation.nav.NavManager
import com.bankin.challenge.base.presentation.viewmodel.BaseAction
import com.bankin.challenge.base.presentation.viewmodel.BaseState
import com.bankin.challenge.base.presentation.viewmodel.BaseViewModel
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.usecase.GetCategoriesListUseCase
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.Action
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState.Error
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState.Loading
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class CategoriesViewModel(
    private val state: SavedStateHandle,
    private val navManager: NavManager,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
) : BaseViewModel<UiState, Action>(Loading) {

    companion object {
        const val DEFAULT_QUERY_NAME = "Jackson"
        private const val SAVED_QUERY_KEY = "query"
    }

    fun onEnter(query: String? = (state.get(SAVED_QUERY_KEY) as? String) ?: DEFAULT_QUERY_NAME) {
        getAlbumList(query)
    }

    private var job: Job? = null

    private fun getAlbumList(query: String?) {
        if (job != null) {
            job?.cancel()
            job = null
        }

        state[SAVED_QUERY_KEY] = query

        job = viewModelScope.launch {
            getCategoriesListUseCase(query).also { result ->
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
