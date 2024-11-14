package com.spacex.rockets.domain.rockets

interface RocketRepository {
    suspend fun getRockets(): List<Rocket>
    suspend fun getRocket(id: String): Rocket
}