package com.spacex.rockets.domain.rockets.di

import com.spacex.rockets.domain.rockets.RocketRepository
import com.spacex.rockets.domain.rockets.SpacexApiRocketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RocketModule {

    @Singleton
    @Binds
    abstract fun bindRocketRepository(repository: SpacexApiRocketRepository): RocketRepository

}