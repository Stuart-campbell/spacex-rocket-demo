@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.spacex.rockets.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.spacex.rockets.R
import com.spacex.rockets.domain.rockets.Rocket
import com.spacex.rockets.ui.components.ErrorComponent
import com.spacex.rockets.ui.components.LoadingComponent
import com.spacex.rockets.ui.components.SharedImageElement

@Composable
fun HomeScreen(
    openDetails: (Rocket) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val viewModel = hiltViewModel<HomeViewModel>()
    LaunchedEffect(Unit) {
        viewModel.loadRockets()
    }

    HomeContent(
        state = viewModel.state,
        openDetails = openDetails,
        retry = { viewModel.loadRockets() },
        sharedImageModifier = SharedImageElement.modifierProvider(sharedTransitionScope, animatedVisibilityScope)
    )
}

@Composable
private fun HomeContent(
    state: HomeViewModel.State,
    openDetails: (Rocket) -> Unit,
    retry: () -> Unit,
    sharedImageModifier: @Composable (String?) -> Modifier
) {
    AnimatedContent(targetState = state, label = "loading-animation") {
        when (it) {
            is HomeViewModel.State.Error -> ErrorComponent(it.exception, retry)
            is HomeViewModel.State.Loaded -> HomeLoadedContent(it.rockets, openDetails, sharedImageModifier)
            HomeViewModel.State.Loading -> LoadingComponent()
        }
    }
}

@Composable
private fun HomeLoadedContent(
    rockets: List<Rocket>,
    openDetails: (Rocket) -> Unit,
    sharedImageModifier: @Composable (String?) -> Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.displayLarge
            )
        }
        items(rockets) { rocket ->
            RocketItem(rocket = rocket, openDetails = openDetails, sharedImageModifier = sharedImageModifier)
        }
    }
}

@Composable
private fun RocketItem(
    rocket: Rocket,
    openDetails: (Rocket) -> Unit,
    sharedImageModifier: @Composable (String?) -> Modifier
) {
    Card(onClick = { openDetails(rocket) }) {
        Column {
            val image = rocket.images.firstOrNull()
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .then(sharedImageModifier(image)),
                contentScale = ContentScale.Crop,
                model = image,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = rocket.title,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserProfilePreview(
    @PreviewParameter(HomePreviewParameterProvider::class) state: HomeViewModel.State
) {
    HomeContent(
        state = state,
        openDetails = {},
        retry = {},
        sharedImageModifier = { Modifier }
    )
}
