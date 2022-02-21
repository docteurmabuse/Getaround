package com.tizzone.getaround.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.domain.usecase.GetCars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CarsViewModel
@Inject constructor(
    private val getCars: GetCars
) : ViewModel() {
    val cars: MutableState<List<Car>> = mutableStateOf(ArrayList())
    val car: MutableState<Car?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    init {
        viewModelScope.launch {
            getCarList()
        }
    }

    private fun getCarList() {
        viewModelScope.launch {
            getCars.invoke().collect { dataState ->
                loading.value = dataState.loading

                dataState.data?.let { list ->
                    cars.value = list
                }

                dataState.error?.let { error ->
                    Timber.e("AppDebug: $error")
                }
            }
        }
    }
}
