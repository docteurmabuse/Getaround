package com.tizzone.getaround.domain.usecase

import com.tizzone.getaround.domain.data.DataState
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCars @Inject constructor(
    private val repository: CarRepository
) {
    suspend operator fun invoke(): Flow<DataState<List<Car>>> = flow {
        try {
            emit(DataState.loading())
            emit(repository.getCars())
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown error"))
        }
    }
}
