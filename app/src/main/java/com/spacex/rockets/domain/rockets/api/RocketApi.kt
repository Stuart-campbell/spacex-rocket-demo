package com.spacex.rockets.domain.rockets.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RocketApi @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getRockets(): List<RocketDto> =
        client.get {
            url {
                path("rockets")
            }
        }.body()

    suspend fun getRocket(id: String): RocketDto =
        client.get {
            url {
                path("rockets", id)
            }
        }.body()

}