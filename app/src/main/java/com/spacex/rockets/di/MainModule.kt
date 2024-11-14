package com.spacex.rockets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideSpacexDataClient(): HttpClient {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return HttpClient {
            install(ContentNegotiation) { json(json) }
            defaultRequest {
                url("https://api.spacexdata.com/v4/") // TODO move to build config
            }
            expectSuccess = true
        }
    }

}