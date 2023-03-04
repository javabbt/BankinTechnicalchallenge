package com.bankin.challenge.feature.album.presentation.screen.categorydetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.bankin.challenge.base.presentation.viewmodel.BaseAction
import com.bankin.challenge.base.presentation.viewmodel.BaseState
import com.bankin.challenge.base.presentation.viewmodel.BaseViewModel
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.domain.usecase.GetSubCategoriesUseCase
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.Action
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState.Loading
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SubCategoriesViewModel(
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase
) : BaseViewModel<UiState, Action>(Loading) {

    fun onEnter(args: CategoryDetailFragmentArgs) {
        getAlbum(args)
    }
    private var job: Job? = null

    private fun getAlbum(args: CategoryDetailFragmentArgs) {
        if (job != null) {
            job?.cancel()
            job = null
        }
        job = viewModelScope.launch {
           getSubCategoriesUseCase(args.id).also { result ->

                val action = when(result){
                    is com.bankin.challenge.base.domain.result.Result.Success -> {
                        Action.AlbumLoadSuccess(result.value)
                    }
                    else ->  Action.AlbumLoadFailure
                }
                sendAction(action = action)
            }
        }
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumLoadSuccess(private val subCat: List<CategoryUiModel>?) : Action {
            override fun reduce(state: UiState) = Content(
                subCat
            )
        }

        object AlbumLoadFailure : Action {
            override fun reduce(state: UiState) = UiState.Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(
            val subCategories: List<CategoryUiModel>?
        ) : UiState

        object Loading : UiState
        object Error : UiState
    }
}
