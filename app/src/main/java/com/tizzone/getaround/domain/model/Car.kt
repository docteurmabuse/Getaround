package com.tizzone.getaround.domain.model

data class Car(
    val id: String,
    val owner: Owner,
    val rating: Rating?,
    val model: String,
    val pricePerDay: Int,
    val brand: String
)
