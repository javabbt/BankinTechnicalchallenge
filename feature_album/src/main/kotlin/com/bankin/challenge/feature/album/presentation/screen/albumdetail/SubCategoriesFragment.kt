package com.bankin.challenge.feature.album.presentation.screen.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import com.bankin.challenge.base.common.res.Dimen
import com.bankin.challenge.base.presentation.activity.BaseFragment
import com.bankin.challenge.base.presentation.compose.composable.DataNotFoundAnim
import com.bankin.challenge.base.presentation.compose.composable.PlaceholderImage
import com.bankin.challenge.base.presentation.compose.composable.ProgressIndicator
import com.bankin.challenge.base.presentation.compose.composable.TextTitleLarge
import com.bankin.challenge.base.presentation.compose.composable.TextTitleMedium
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState.Error
import com.bankin.challenge.feature.album.presentation.screen.albumdetail.SubCategoriesViewModel.UiState.Loading
import com.igorwojda.showcase.feature.album.R
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.navigation.koinNavGraphViewModel

internal class AlbumDetailFragment : BaseFragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()
    private val model: SubCategoriesViewModel by koinNavGraphViewModel(R.id.albumNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        model.onEnter(args)

        return ComposeView(requireContext()).apply {
            setContent {
                AlbumDetailScreen(uiStateFlow = model.uiStateFlow)
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun AlbumDetailScreen(uiStateFlow: StateFlow<UiState>) {
    val uiState: UiState by uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> PhotoDetails(it)
        }
    }
}

@Composable
private fun PhotoDetails(content: Content) {
    Column(
        modifier = Modifier
            .padding(Dimen.screenContentPadding)
            .verticalScroll(rememberScrollState()),

    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(Dimen.spaceM)
                .wrapContentSize()
                .size(320.dp)
                .align(CenterHorizontally),
        ) {
            PlaceholderImage(
                url = "",
                contentDescription = stringResource(id = R.string.album_cover_content_description),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(Dimen.spaceL))
        TextTitleLarge(text = "")
        TextTitleMedium(text = "")
        Spacer(modifier = Modifier.height(Dimen.spaceL))
    }
}


