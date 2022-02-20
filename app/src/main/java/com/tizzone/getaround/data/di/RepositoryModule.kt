package com.tizzone.getaround.data.di

import com.tizzone.getaround.data.network.CarsApi
import com.tizzone.getaround.domain.repository.CarRepository
import com.tizzone.getaround.domain.repository.CarRepositoryImpl
import com.tizzone.getaround.domain.usecase.GetCars
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideGetCars(
        repository: CarRepository
    ): GetCars {
        return GetCars(
            repository = repository
        )
    }
    @Provides
    @Singleton
    fun provideRepository(
        carsApi: CarsApi
    ): CarRepository {
        return CarRepositoryImpl(
            service = carsApi
        )
    }
}
