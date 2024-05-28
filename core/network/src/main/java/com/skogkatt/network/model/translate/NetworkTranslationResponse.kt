package com.skogkatt.network.model.translate

import kotlinx.serialization.Serializable

@Serializable
data class NetworkTranslationResponse(
    val translations: List<Translation>,
)

@Serializable
data class Translation(
    val text: String,
)
