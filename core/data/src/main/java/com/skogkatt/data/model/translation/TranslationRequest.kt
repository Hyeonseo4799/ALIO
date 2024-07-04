package com.skogkatt.data.model.translation

import com.skogkatt.model.translation.Translation
import com.skogkatt.network.model.translation.TranslationRequest

internal fun Translation.toTranslationRequest() = TranslationRequest(
    texts = texts,
)
