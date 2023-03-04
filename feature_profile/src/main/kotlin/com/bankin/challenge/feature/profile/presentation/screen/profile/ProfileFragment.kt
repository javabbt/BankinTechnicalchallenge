package com.bankin.challenge.feature.profile.presentation.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.bankin.challenge.base.presentation.activity.BaseFragment
import com.bankin.challenge.base.presentation.compose.composable.UnderConstructionAnim

class ProfileFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileScreen()
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreen() {
    UnderConstructionAnim()
}
