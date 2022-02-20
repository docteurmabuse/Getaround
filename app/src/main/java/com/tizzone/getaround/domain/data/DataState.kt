package com.tizzone.getaround.domain.data

/**
 * Class to use for data state with StateFlow in this app
 */
class DataState<out T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: String? = null
) {
    companion object {
        fun <T> success(data: T?): DataState<T> {
            return DataState(
                data = data,
            )
        }

        fun <T> error(message: String): DataState<T> {
            return DataState(
                error = message
            )
        }

        fun <T> loading(): DataState<T> = DataState(loading = true)
    }
}
