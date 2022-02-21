package com.tizzone.getaround.utils

import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.domain.model.Owner
import com.tizzone.getaround.domain.model.Rating

const val BASE_URL = "https://raw.githubusercontent.com/drivy/jobs/master/mobile/"
const val CARS_VIEWMODEL = "CarsViewModel"
const val CAR_INDEX_URL = "/{carIndex}"
const val CAR_INDEX = "carIndex"
val FAKE_CAR = Car(
    "1",
    brand = "Citroen",
    model = "C3",
    pricePerDay = 17,
    rating = Rating(4.69771, 259),
    owner = Owner(
        name = "Elmira Sorrell",
        pictureUrl = "https://github.com/drivy/mobile-technical-test/raw/master/api/pictures/13.jpg",
        rating = Rating(4.69771, 259)
    )
)
val FAKE_CAR_LIST = listOf(
    Car(
        "1",
        brand = "Citroen",
        model = "C3",
        pricePerDay = 17,
        rating = Rating(4.69771, 259),
        owner = Owner(
            name = "Elmira Sorrell",
            pictureUrl = "https://github.com/drivy/mobile-technical-test/raw/master/api/pictures/13.jpg",
            rating = Rating(4.69771, 259)
        )
    ),
    Car(
        "2",
        brand = "Citroen",
        model = "C3",
        pricePerDay = 17,
        rating = Rating(4.69771, 259),
        owner = Owner(
            name = "Elmira Sorrell",
            pictureUrl = "https://github.com/drivy/mobile-technical-test/raw/master/api/pictures/13.jpg",
            rating = Rating(4.69771, 259)
        )
    ),
)
