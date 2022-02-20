package com.tizzone.getaround.domain.repository

import com.tizzone.getaround.data.network.CarsApi
import com.tizzone.getaround.domain.data.DataState
import com.tizzone.getaround.domain.model.Car
import javax.inject.Inject

sealed interface CarRepository {
    suspend fun getCars(): DataState<List<Car>>
}

class CarRepositoryImpl @Inject constructor(
    private val service: CarsApi
) : CarRepository {
    override suspend fun getCars(): DataState<List<Car>> {
        val response = service.getAsyncCars()
        return when (response.isSuccessful) {
            true -> DataState.success(
                response.body()!!.map { it.toDomain() }
            )
            false -> DataState.error(response.errorBody().toString())
        }
    }
}
