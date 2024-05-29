package com.skogkatt.data.datasource.translation

import com.skogkatt.network.model.translation.NetworkTranslationRequest
import com.skogkatt.network.model.translation.NetworkTranslationResponse

internal interface TranslationDataSource {
    suspend fun translate(body: NetworkTranslationRequest): NetworkTranslationResponse
}
