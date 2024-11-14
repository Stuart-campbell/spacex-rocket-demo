package com.spacex.rockets.preview

import com.spacex.rockets.domain.rockets.Rocket

object RocketPreviewHelper {

    fun createPreviewRocket() = Rocket(
        id = "",
        title = "Falcon 1",
        images = listOf("https://imgur.com/DaCfMsj.jpg"),
        description = "The Falcon 1 was an expendable launch system privately developed and manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became the first privately-developed liquid-fuel launch vehicle to go into orbit around the Earth.",
        heightMeters = 22.25,
        massKg = 30146.0,
        maxPayloadMassKg = 450.0,
        wikipedia = ""
    )

}