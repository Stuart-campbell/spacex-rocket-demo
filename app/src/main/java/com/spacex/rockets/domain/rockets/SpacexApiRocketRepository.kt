package com.spacex.rockets.domain.rockets

import com.spacex.rockets.domain.rockets.api.RocketApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpacexApiRocketRepository @Inject constructor(
    private val rocketApi: RocketApi,
    private val mapper: RocketDtoMapper
): RocketRepository {

    override suspend fun getRockets(): List<Rocket> {
        with(mapper) {
            return rocketApi.getRockets().map { it.toRocket() }
        }
    }

}