package com.tizzone.getaround.data.network

import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.domain.model.Owner
import com.tizzone.getaround.domain.model.Rating

object MockWebServerResponse {
    val MOCK_CAR_LIST = listOf(
        Car(
            id = "1",
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
            id = "2",
            brand = "Renault",
            model = "Clio",
            pricePerDay = 20,
            rating = Rating(4.69771, 270),
            owner = Owner(
                name = "Elvira Snorrell",
                pictureUrl = "https://github.com/drivy/mobile-technical-test/raw/master/api/pictures/15.jpg",
                rating = Rating(4.69771, 259)
            )
        )
    )
    val MOCK_CAR = Car(
        id = "1",
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
}
