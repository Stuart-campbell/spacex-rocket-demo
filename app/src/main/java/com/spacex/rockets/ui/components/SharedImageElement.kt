@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.spacex.rockets.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object SharedImageElement {

    fun modifierProvider(sharedTransitionScope: SharedTransitionScope, animatedVisibilityScope: AnimatedVisibilityScope): @Composable (String?) -> Modifier = {
        with(sharedTransitionScope) {
            Modifier.sharedElement(
                sharedTransitionScope.rememberSharedContentState(key = "image-$it"),
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}