package com.jetpack.composepoc.data

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()

    sealed class Error(val message: String) : Result<Nothing>() {
        class NetworkError(msg: String = "Network Error") : Error(msg)
        class TimeoutError(msg: String = "Timeout Error") : Error(msg)
        class NotFoundError(msg: String = "User Not Found") : Error(msg)
        class UnknownError(msg: String = "Unknown Error") : Error(msg)
    }
}
