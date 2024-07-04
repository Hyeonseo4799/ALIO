package com.skogkatt.data.repository.translation

import com.skogkatt.data.datasource.translation.TranslationDataSource
import com.skogkatt.data.model.translation.toTranslationRequest
import com.skogkatt.model.translation.Translation
import javax.inject.Inject

internal class TranslationRepositoryImpl @Inject constructor(
    private val translationDataSource: TranslationDataSource
): TranslationRepository {
    override suspend fun translate(body: Translation): String {
        return translationDataSource.translate(body.toTranslationRequest())
            .translations.joinToString { it.text }
    }
}
