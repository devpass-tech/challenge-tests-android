package com.devpass.spaceapp.utils

sealed interface NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error<T>(val exception: Throwable) : NetworkResult<T>
}