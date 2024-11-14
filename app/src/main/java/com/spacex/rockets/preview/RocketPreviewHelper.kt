package com.spacex.rockets.preview

import com.spacex.rockets.domain.rockets.Rocket

object RocketPreviewHelper {

    fun createPreviewRocket() = Rocket(
        id = "",
        title = "Falcon 1",
        images = listOf("https://imgur.com/DaCfMsj.jpg")
    )

}