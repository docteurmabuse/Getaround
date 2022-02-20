package com.tizzone.getaround.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tizzone.getaround.R
import com.tizzone.getaround.presentation.ui.CarsViewModel
import com.tizzone.getaround.presentation.ui.components.CarList

@Composable
fun CarListScreen(
    viewModel: CarsViewModel,
    onNavigateToCarDetail: (String) -> Unit
) {
    val cars = viewModel.cars.value
    val loading = viewModel.loading.value

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
    } else if (cars.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CarList(cars = cars, onNavigateToCarDetail = onNavigateToCarDetail)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.app_name)
            )
        }
    }
}
