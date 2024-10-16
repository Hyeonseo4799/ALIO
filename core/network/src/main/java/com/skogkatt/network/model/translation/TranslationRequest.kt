package com.skogkatt.network.model.translation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationRequest(
    @SerialName("text") val texts: List<String>,
    @SerialName("source_lang") val sourceLang: String,
    @SerialName("target_lang") val targetLang: String,
)
