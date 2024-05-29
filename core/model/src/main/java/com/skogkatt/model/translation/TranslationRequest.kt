package com.skogkatt.model.translation

data class TranslationRequest(
    val texts: List<String>,
    val sourceLang: String = "EN",
    val targetLang: String = "KO",
)
