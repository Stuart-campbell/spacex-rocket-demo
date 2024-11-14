package com.spacex.rockets.domain.rockets

import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    val id: String,
    val title: String,
    val images: List<String>
)