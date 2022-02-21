package com.tizzone.getaround.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tizzone.getaround.R
import com.tizzone.getaround.presentation.ui.CarsViewModel
import com.tizzone.getaround.presentation.ui.components.CarDetails
import timber.log.Timber

@Composable
fun CardDetailsScreen(
    viewModel: CarsViewModel,
    index: Int?,
    navigateUp: () -> Unit,
) {
    val cars = viewModel.cars

    val loading = viewModel.loading.value

    if (index == null) {
        Timber.d("Appdebug : Error")
    } else {
        if (loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.app_name)
                )
            }
        } else if (cars.value.isNotEmpty()) {
            val car = cars.value[index]
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                item {
                    CarDetails(
                        car = car,
                        navigateUp = navigateUp
                    )
                }
            }
        }
    }
}
