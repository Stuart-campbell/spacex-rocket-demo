package com.spacex.rockets.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.spacex.rockets.domain.rockets.Rocket
import com.spacex.rockets.ui.components.ErrorComponent
import com.spacex.rockets.ui.components.LoadingComponent

@Composable
fun HomeScreen(openDetails: (String) -> Unit) {

    val viewModel = hiltViewModel<HomeViewModel>()
    LaunchedEffect(Unit) {
        viewModel.loadRockets()
    }

    HomeContent(
        state = viewModel.state,
        openDetails = openDetails,
        retry = { viewModel.loadRockets() }
    )
}

@Composable
private fun HomeContent(
    state: HomeViewModel.State,
    openDetails: (String) -> Unit,
    retry: () -> Unit
) {
    when(state) {
        is HomeViewModel.State.Error -> ErrorComponent(state.exception, retry)
        is HomeViewModel.State.Loaded -> HomeLoadedContent(state.rockets, openDetails)
        HomeViewModel.State.Loading -> LoadingComponent()
    }
}

@Composable
private fun HomeLoadedContent(
    rockets: List<Rocket>,
    openDetails: (String) -> Unit,
) {

    Scaffold {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = it) {
            items(rockets) { rocket ->
                RocketItem(rocket = rocket, openDetails = openDetails)
            }
        }
    }

}


@Composable
private fun RocketItem(
    rocket: Rocket,
    openDetails: (String) -> Unit,
) {
    Text(text = rocket.id)
}

@Preview
@Composable
fun UserProfilePreview(
    @PreviewParameter(HomePreviewParameterProvider::class) state: HomeViewModel.State
) {
    HomeContent(
        state = state,
        openDetails = {},
        retry = {}
    )
}
