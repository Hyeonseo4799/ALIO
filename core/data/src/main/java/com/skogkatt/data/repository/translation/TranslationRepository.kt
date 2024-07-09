package com.skogkatt.data.repository.translation

import com.skogkatt.model.translation.Translation

interface TranslationRepository {
    suspend fun translate(body: Translation): List<String>
}
