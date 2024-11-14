@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.spacex.rockets

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.spacex.rockets.details.DetailsScreen
import com.spacex.rockets.home.HomeScreen
import kotlinx.serialization.Serializable


sealed class Route {
    @Serializable
    data object Home: Route()
    @Serializable
    data class Details(
        val id: String,
        val title: String,
        val image: String?
    ): Route()
}

@Composable
fun MainNavigation() {

    val controller = rememberNavController()
    SharedTransitionLayout {
        NavHost(navController = controller, Route.Home) {
            composable<Route.Home> {
                HomeScreen(
                    openDetails = { controller.navigate(Route.Details(it.id, it.title, it.images.firstOrNull())) },
                    animatedVisibilityScope = this@composable,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
            composable<Route.Details> {
                DetailsScreen(
                    route = it.toRoute(),
                    animatedVisibilityScope = this@composable,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }

}