package com.bankin.challenge.feature.album.presentation.screen.categorydetail

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
import androidx.navigation.fragment.navArgs
import com.bankin.challenge.base.common.res.Dimen
import com.bankin.challenge.base.presentation.activity.BaseFragment
import com.bankin.challenge.base.presentation.compose.composable.DataNotFoundAnim
import com.bankin.challenge.base.presentation.compose.composable.ProgressIndicator
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState.Content
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState.Error
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel.UiState.Loading
import com.igorwojda.showcase.feature.album.R
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.navigation.koinNavGraphViewModel
import kotlin.random.Random

internal class CategoryDetailFragment : BaseFragment() {
    private val args: CategoryDetailFragmentArgs by navArgs()
    private val model: SubCategoriesViewModel by koinNavGraphViewModel(R.id.albumNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        model.onEnter(args)

        return ComposeView(requireContext()).apply {
            setContent {
                SubCategoriesScreen(uiStateFlow = model.uiStateFlow)
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun SubCategoriesScreen(uiStateFlow: StateFlow<UiState>) {
    val uiState: UiState by uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> PhotoDetails(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoDetails(content: Content) {
    if (content.subCategories!!.isEmpty().not()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(Dimen.imageSize),
            contentPadding = PaddingValues(Dimen.screenContentPadding),
        ) {
            items(content.subCategories.size) { index ->
                val sub = content.subCategories[index]

                ElevatedCard(
                    modifier = Modifier
                        .padding(Dimen.spaceS)
                        .wrapContentSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(
                            Random.nextInt(256),
                            Random.nextInt(256),
                            Random.nextInt(256),
                        ),
                        contentColor = Color.White
                    ),
                ) {
                    Text(
                        text = sub.name ?: "",
                        modifier = Modifier
                            .padding(15.dp)
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


