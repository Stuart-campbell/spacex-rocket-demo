package com.spacex.rockets.domain.rockets

import com.spacex.rockets.domain.rockets.api.RocketDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RocketDtoMapper @Inject constructor() {

    fun RocketDto.toRocket(): Rocket {
        return Rocket(
            id = id,
            title = name,
            images = images,
            description = description,
            heightMeters = height.meters,
            massKg = mass.kg,
            maxPayloadMassKg = payloadWeights.maxOf { it.kg },
            wikipedia = wikipedia
        )
    }

}