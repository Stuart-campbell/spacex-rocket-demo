package com.spacex.rockets.domain.rockets

import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    val id: String,
    val title: String,
    val images: List<String>,
    val description: String,
    val heightMeters: Double,
    val massKg: Double,
    val maxPayloadMassKg: Double,
    val wikipedia: String
)