package com.tizzone.getaround.data.network

import com.tizzone.getaround.data.network.model.CarDtoModel
import retrofit2.Response
import retrofit2.http.GET

interface CarsApi {
    @GET("api/cars.json")
    suspend fun getAsyncCars(): Response<List<CarDtoModel>>
}
