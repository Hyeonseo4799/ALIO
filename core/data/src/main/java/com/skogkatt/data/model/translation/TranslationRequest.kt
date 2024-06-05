package com.skogkatt.data.model.translation

import com.skogkatt.model.translation.TranslationRequest
import com.skogkatt.network.model.translation.NetworkTranslationRequest

internal fun TranslationRequest.toNetworkTranslationRequest() = NetworkTranslationRequest(
    texts = texts,
    sourceLang = sourceLang,
    targetLang = targetLang,
)
