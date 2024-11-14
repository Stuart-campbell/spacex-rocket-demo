@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)

package com.spacex.rockets.details

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.spacex.rockets.Route
import com.spacex.rockets.domain.rockets.Rocket
import com.spacex.rockets.ui.components.ErrorComponent
import com.spacex.rockets.ui.components.LoadingComponent
import com.spacex.rockets.ui.components.SharedImageElement

@Composable
fun DetailsScreen(
    route: Route.Details,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val viewModel = hiltViewModel<DetailsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.loadRocket(route.id)
    }

    DetailsContent(
        title = route.title,
        image = route.image,
        state = viewModel.state,
        retry = { viewModel.loadRocket(route.id) },
        sharedImageModifier = SharedImageElement.modifierProvider(sharedTransitionScope, animatedVisibilityScope)
    )
}

@Composable
private fun DetailsContent(
    title: String,
    image: String?,
    state: DetailsViewModel.State,
    retry: () -> Unit,
    sharedImageModifier: @Composable (String?) -> Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = {
                    val backPressedOwner = LocalOnBackPressedDispatcherOwner.current
                    IconButton(onClick = { backPressedOwner?.onBackPressedDispatcher?.onBackPressed() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())) {

            when (state) {
                is DetailsViewModel.State.Loaded -> ImagesCarousel(state.rocket, sharedImageModifier)
                else -> AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .then(sharedImageModifier(image)),
                    contentScale = ContentScale.Crop,
                    model = image,
                    contentDescription = null
                )
            }

            AnimatedContent(targetState = state, label = "loading-animation") {
                when (it) {
                    is DetailsViewModel.State.Error -> ErrorComponent(it.exception, retry)
                    is DetailsViewModel.State.Loaded -> DetailsLoadedContent(it.rocket)
                    DetailsViewModel.State.Loading -> LoadingComponent()
                }
            }
        }
    }
}

@Composable
private fun ImagesCarousel(
    rocket: Rocket,
    sharedImageModifier: @Composable (String?) -> Modifier
) {
    val state = rememberPagerState {
        rocket.images.size
    }
    Box {
        HorizontalPager(state = state) {
            val image = rocket.images[it]
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .then(sharedImageModifier(image)),
                contentScale = ContentScale.Crop,
                model = image,
                contentDescription = null
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(state.pageCount) { iteration ->
                val color = if (state.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(6.dp)
                )
            }
        }
    }
}

@Composable
private fun DetailsLoadedContent(
    rocket: Rocket
) {

}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview(
    @PreviewParameter(DetailsPreviewParameterProvider::class) state: DetailsViewModel.State
) {
    DetailsContent(
        title = "Falcon 1",
        image = "",
        state = state,
        retry = {},
        sharedImageModifier = { Modifier }
    )
}