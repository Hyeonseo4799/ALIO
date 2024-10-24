package com.skogkatt.data.datasource.remote.translation

import com.skogkatt.network.model.translation.TranslationRequest
import com.skogkatt.network.model.translation.TranslationResponse

internal interface TranslationDataSource {
    suspend fun translate(body: TranslationRequest): TranslationResponse
}
