package com.skogkatt.data.repository.translation

import com.skogkatt.data.datasource.translation.TranslationDataSource
import com.skogkatt.data.model.translation.toNetworkTranslationRequest
import com.skogkatt.model.translation.TranslationRequest
import javax.inject.Inject

internal class TranslationRepositoryImpl @Inject constructor(
    private val translationDataSource: TranslationDataSource
): TranslationRepository {
    override suspend fun translate(body: TranslationRequest): String {
        return translationDataSource.translate(body.toNetworkTranslationRequest())
            .translations.joinToString { it.text }
    }
}
