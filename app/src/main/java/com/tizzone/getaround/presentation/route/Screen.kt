package com.tizzone.getaround.presentation.route

sealed class Screen(
    val route: String,
) {
    object CarsList : Screen("carsList")
    object CarDetail : Screen("carDetail")
}
