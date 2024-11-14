package com.spacex.rockets.domain.rockets.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDto(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("flickr_images")
    val images: List<String>
)
