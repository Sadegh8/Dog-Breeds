package com.example.dogbreeds.di

import com.example.dogbreeds.data.repository.BreedsRepositoryImpl
import com.example.dogbreeds.domain.repository.BreedsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsBreedRepository(
        breedsRepositoryImpl: BreedsRepositoryImpl
    ): BreedsRepository
}