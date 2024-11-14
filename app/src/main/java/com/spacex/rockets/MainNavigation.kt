@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.spacex.rockets

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spacex.rockets.home.HomeScreen
import kotlinx.serialization.Serializable


sealed class Route {
    @Serializable
    data object Home: Route()
    @Serializable
    data class Details(val id: String): Route()
}

@Composable
fun MainNavigation() {

    val controller = rememberNavController()
    SharedTransitionLayout {
        NavHost(navController = controller, Route.Home) {
            composable<Route.Home> {
                HomeScreen {
                    // TODO navigate to details
//                    controller.navigate(Route.Details(it))
                }
            }

        }
    }

}