package com.l.uoltest.data.model

import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

sealed class Result<out T> {

    object Loading : Result<Nothing>()

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: Throwable) : Result<Nothing>() {
        fun getError(): ErrorEntity {
            exception.printStackTrace()

            return when (exception) {
                is IOException -> ErrorEntity.Network
                is HttpException -> {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                        HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                        else -> ErrorEntity.Unknown
                    }
                }
                else -> ErrorEntity.Unknown
            }
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "_RESULT_ Success data: $data"
            is Error -> "_RESULT_ Error exception: ${getError()}"
            Loading -> "_RESULT_ Loading"
        }
    }
}