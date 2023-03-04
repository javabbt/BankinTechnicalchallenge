package com.bankin.challenge.feature.album.presentation.screen.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bankin.challenge.base.common.res.Dimen
import com.bankin.challenge.base.presentation.activity.BaseFragment
import com.bankin.challenge.base.presentation.compose.composable.DataNotFoundAnim
import com.bankin.challenge.base.presentation.compose.composable.ProgressIndicator
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState.Error
import com.bankin.challenge.feature.album.presentation.screen.albumlist.CategoriesViewModel.UiState.Loading
import com.igorwojda.showcase.feature.album.R
import org.koin.androidx.navigation.koinNavGraphViewModel
import kotlin.random.Random

class CategoriestFragment : BaseFragment() {

    private val model: CategoriesViewModel by koinNavGraphViewModel(R.id.albumNavGraph)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AlbumListScreen(model)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.onEnter()
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun AlbumListScreen(viewModel: CategoriesViewModel) {
    val uiState: UiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> PhotoGrid(categoryUiModels = it.categoryUiModels, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoGrid(categoryUiModels: List<CategoryUiModel>, viewModel: CategoriesViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimen.imageSize),
        contentPadding = PaddingValues(Dimen.screenContentPadding),
    ) {
        items(categoryUiModels.size) { index ->
            val album = categoryUiModels[index]

            ElevatedCard(
                modifier = Modifier
                    .padding(Dimen.spaceS)
                    .wrapContentSize(),
                onClick = { viewModel.onAlbumClick(album) },
                colors = CardDefaults.cardColors(
                    containerColor =  Color(
                        Random.nextInt(256),
                        Random.nextInt(256),
                        Random.nextInt(256),
                    ),
                    contentColor = Color.White
                ),
            ) {
                Text(
                    text = album.name ?: "",
                    modifier = Modifier.padding(15.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
