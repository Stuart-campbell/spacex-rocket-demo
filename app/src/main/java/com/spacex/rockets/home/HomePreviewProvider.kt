package com.spacex.rockets.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spacex.rockets.domain.rockets.Rocket

class HomePreviewParameterProvider : PreviewParameterProvider<HomeViewModel.State> {
    override val values = sequenceOf(
        HomeViewModel.State.Loading,
        HomeViewModel.State.Loaded(listOf(Rocket(""))),
        HomeViewModel.State.Error(IllegalStateException())
    )
}