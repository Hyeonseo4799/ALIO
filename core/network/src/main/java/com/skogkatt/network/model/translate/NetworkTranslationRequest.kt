package com.skogkatt.network.model.translate

import kotlinx.serialization.SerialName

data class NetworkTranslationRequest(
    @SerialName("text") val texts: List<String>,
    @SerialName("source_lang") val sourceLang: String = "EN",
    @SerialName("target_lang") val targetLang: String = "KO",
)

