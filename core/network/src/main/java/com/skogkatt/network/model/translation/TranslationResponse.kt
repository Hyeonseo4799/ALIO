package com.skogkatt.network.model.translation

import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponse(
    val translations: List<Translation>,
)

@Serializable
data class Translation(
    val text: String,
)
