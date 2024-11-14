package com.spacex.rockets.domain.rockets

import com.karumi.kotlinsnapshot.matchWithSnapshot
import com.spacex.rockets.domain.rockets.api.RocketApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class SpacexApiRocketRepositoryTest {


    @Test
    fun getRockets() {
        runBlocking {
            val repository = rocketRepository(rockets)

            val actual = repository.getRockets()

            actual.matchWithSnapshot()
        }
    }

}

private fun rocketRepository(jsonInput: String): RocketRepository {
    val mockEngine = MockEngine { _ ->
        respond(
            content = ByteReadChannel(jsonInput),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    val json = Json {
        ignoreUnknownKeys = true
    }

    val client = HttpClient(mockEngine) {
        install(ContentNegotiation) { json(json) }
    }
    val rocketsApi = RocketApi(client)

    return SpacexApiRocketRepository(rocketsApi, RocketDtoMapper())
}