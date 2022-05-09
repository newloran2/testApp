package com.l.uoltest.data.model

sealed class ErrorEntity {
    object Network : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object Unknown : ErrorEntity()
}
