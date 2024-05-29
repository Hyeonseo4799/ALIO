package com.skogkatt.data.datasource.translation

import com.skogkatt.network.api.DeepLApi
import com.skogkatt.network.model.translation.NetworkTranslationRequest
import com.skogkatt.network.model.translation.NetworkTranslationResponse
import javax.inject.Inject

internal class TranslationDataSourceImpl @Inject constructor(
    private val deepLApi: DeepLApi
): TranslationDataSource {
    override suspend fun translate(body: NetworkTranslationRequest): NetworkTranslationResponse {
        return deepLApi.translate(body = body)
    }
}
