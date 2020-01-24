package com.gabrielhayon.reddit.client.repository

data class ServiceResponse(
    val code: ResponseCode,
    var response: Any? = null,
    var lastUpdate: Long? = null
) {
    companion object {
        fun buildSuccessful(response: Any): ServiceResponse {
            return ServiceResponse(ResponseCode.OK, response, System.currentTimeMillis())
        }

        fun buildNetworkError(): ServiceResponse {
            return ServiceResponse(ResponseCode.NETWORK_ERROR)
        }

        fun buildServiceError(): ServiceResponse {
            return ServiceResponse(ResponseCode.SERVICE_ERROR)
        }
    }
}

enum class ResponseCode {
    OK, NETWORK_ERROR, SERVICE_ERROR
}