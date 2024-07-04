package com.skogkatt.data.repository.translation

import com.skogkatt.model.translation.Translation

internal interface TranslationRepository {
    suspend fun translate(body: Translation): String
}
