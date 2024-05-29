package com.skogkatt.data.repository.translation

import com.skogkatt.model.translation.TranslationRequest

internal interface TranslationRepository {
    suspend fun translate(body: TranslationRequest): String
}
