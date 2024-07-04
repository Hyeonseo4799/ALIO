package com.skogkatt.data.datasource.translation

import com.skogkatt.network.model.translation.TranslationRequest
import com.skogkatt.network.model.translation.TranslationResponse

internal interface TranslationDataSource {
    suspend fun translate(body: TranslationRequest): TranslationResponse
}
