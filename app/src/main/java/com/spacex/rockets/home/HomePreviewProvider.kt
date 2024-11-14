package com.spacex.rockets.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spacex.rockets.preview.RocketPreviewHelper

class HomePreviewParameterProvider : PreviewParameterProvider<HomeViewModel.State> {
    override val values = sequenceOf(
        HomeViewModel.State.Loading,
        HomeViewModel.State.Loaded(listOf(RocketPreviewHelper.createPreviewRocket())),
        HomeViewModel.State.Error(IllegalStateException())
    )
}
