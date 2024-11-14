package com.spacex.rockets.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spacex.rockets.preview.RocketPreviewHelper

class DetailsPreviewParameterProvider : PreviewParameterProvider<DetailsViewModel.State> {
    override val values = sequenceOf(
        DetailsViewModel.State.Loading,
        DetailsViewModel.State.Loaded(RocketPreviewHelper.createPreviewRocket()),
        DetailsViewModel.State.Error(IllegalStateException())
    )
}

