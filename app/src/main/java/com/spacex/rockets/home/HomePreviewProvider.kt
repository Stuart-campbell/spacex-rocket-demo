package com.spacex.rockets.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spacex.rockets.domain.rockets.Rocket

class HomePreviewParameterProvider : PreviewParameterProvider<HomeViewModel.State> {
    override val values = sequenceOf(
        HomeViewModel.State.Loading,
        HomeViewModel.State.Loaded(listOf(createPreviewRocket())),
        HomeViewModel.State.Error(IllegalStateException())
    )
}

private fun createPreviewRocket() = Rocket(
    id = "",
    title = "Falcon 1",
    image = "https://imgur.com/DaCfMsj.jpg"
)
