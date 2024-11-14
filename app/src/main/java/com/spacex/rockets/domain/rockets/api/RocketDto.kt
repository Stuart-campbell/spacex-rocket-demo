package com.spacex.rockets.domain.rockets.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDto(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("flickr_images")
    val images: List<String>,
    val height: Height,
    val mass: Mass,
    @SerialName("payload_weights")
    val payloadWeights: List<PayloadWeight>,
    val wikipedia: String
) {

    @Serializable
    data class Height(
        val meters: Double
    )

    @Serializable
    data class Mass(
        val kg: Double
    )

    @Serializable
    data class PayloadWeight(
        val kg: Double
    )

}
