package com.skogkatt.network.api.retrofit

import com.skogkatt.network.api.Api
import com.skogkatt.network.api.ApiType
import com.skogkatt.network.model.translation.TranslationRequest
import com.skogkatt.network.model.translation.TranslationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepLApi {
    @POST("v2/translate")
    @Api(ApiType.DEEPL)
    suspend fun translate(
        @Body body: TranslationRequest
    ): TranslationResponse
}
