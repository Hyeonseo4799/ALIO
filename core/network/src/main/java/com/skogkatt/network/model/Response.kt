package com.skogkatt.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val response: T
)