package com.skogkatt.data.datasource.translation

import com.skogkatt.network.api.retrofit.DeepLApi
import com.skogkatt.network.model.translation.TranslationRequest
import com.skogkatt.network.model.translation.TranslationResponse
import javax.inject.Inject

internal class TranslationDataSourceImpl @Inject constructor(
    private val deepLApi: DeepLApi
) : TranslationDataSource {
    override suspend fun translate(body: TranslationRequest): TranslationResponse {
        return deepLApi.translate(body)
    }
}
