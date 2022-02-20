package com.tizzone.getaround.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.presentation.route.Screen
import com.tizzone.getaround.presentation.theme.GetaroundTheme
import com.tizzone.getaround.utils.FAKE_CAR_LIST

@Composable

fun CarList(
    cars: List<Car>,
    onNavigateToCarDetail: (String) -> Unit
) {

    LazyColumn(
        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
    ) {
        itemsIndexed(
            items = cars
        ) {
            index, car ->
            CarCard(
                modifier = Modifier,
                car = car,
                onClick = {
                    val route = Screen.CarDetail.route + "/${car.id}"
                    onNavigateToCarDetail(route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListPreview() {
    GetaroundTheme {
        CarList(
            cars = FAKE_CAR_LIST,
            onNavigateToCarDetail = {}
        )
    }
}
