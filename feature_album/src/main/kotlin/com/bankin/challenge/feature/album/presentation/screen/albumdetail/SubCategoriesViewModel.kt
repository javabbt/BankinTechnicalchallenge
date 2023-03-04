package com.bankin.challenge.feature.album.presentation.screen.albumdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.bankin.challenge.base.presentation.viewmodel.BaseAction
import com.bankin.challenge.base.presentation.viewmodel.BaseState
import com.bankin.challenge.base.presentation.viewmodel.BaseViewModel
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.Action
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState.Loading
import kotlinx.coroutines.launch

internal class SubCategoriesViewModel(

) : BaseViewModel<UiState, Action>(Loading) {

    fun onEnter(args: AlbumDetailFragmentArgs) {
        getAlbum(args)
    }

    private fun getAlbum(args: AlbumDetailFragmentArgs) {
        viewModelScope.launch {

        }
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumLoadSuccess(private val categoryUiModel: CategoryUiModel) : Action {
            override fun reduce(state: UiState) = Content(
                name = categoryUiModel.name ?: ""
            )
        }

        object AlbumLoadFailure : Action {
            override fun reduce(state: UiState) = UiState.Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(
           val name: String
        ) : UiState

        object Loading : UiState
        object Error : UiState
    }
}
