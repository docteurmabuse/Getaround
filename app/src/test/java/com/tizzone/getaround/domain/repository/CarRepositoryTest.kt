package com.tizzone.getaround.domain.repository

import com.google.gson.GsonBuilder
import com.tizzone.getaround.data.network.CarsApi
import com.tizzone.getaround.data.network.MockWebServerResponse.carsResponse
import com.tizzone.getaround.domain.model.Car
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HttpsURLConnection

class CarRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl

    // System in test
    private lateinit var apiService: CarsApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("")

        apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            )
            .build()
            .create(CarsApi::class.java)
    }

    @Test
    fun testGeCarsFromNetworkIsWorking(): Unit = runBlocking {
        // Condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(carsResponse)
        )
        var cars = listOf<Car>()
        // Car list should be empty
        assert(cars.isEmpty())
        // Adding api result of cars in the list
        cars = apiService.getAsyncCars().let { it ->
            it.body()!!.map { it.toDomain() }
        }
        // Car list should not be empty
        assert(cars.isNotEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
