package com.tizzone.getaround.domain.repository

import androidx.compose.runtime.mutableStateOf
import com.google.gson.GsonBuilder
import com.tizzone.getaround.data.network.CarsApi
import com.tizzone.getaround.data.network.MockWebServerResponse
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.domain.usecase.GetCars
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HttpsURLConnection

class GetCarsUseCaseTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl

    // System in test
    private lateinit var apiService: CarsApi
    private lateinit var getCars: GetCars

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
        getCars = GetCars(repository = CarRepositoryImpl(apiService))
    }

    @Test
    fun testGeCarsUseCaseIsWorking(): Unit = runBlocking {
        // Condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(MockWebServerResponse.carsResponse)
        )
        val carsFlow = getCars.invoke()
        val actual = mutableStateOf<List<Car>>(listOf())
        val isLoading = mutableStateOf(true)
        // Car list should be empty
        assert(isLoading.value)
        assert(actual.value.isEmpty())

        carsFlow.collect {
            isLoading.value = it.loading
            it.data?.let { cars -> actual.value = cars }
        }

        // Loading should be false
        assert(!isLoading.value)
        // Car list should not be empty
        assert(actual.value.isNotEmpty())
        assert(!isLoading.value)
        Assert.assertEquals(3, actual.value.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
