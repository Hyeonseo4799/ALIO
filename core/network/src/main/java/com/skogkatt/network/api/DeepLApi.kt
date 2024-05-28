package com.skogkatt.network.api

import com.skogkatt.network.model.translation.NetworkTranslationRequest
import com.skogkatt.network.model.translation.NetworkTranslationResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface DeepLApi {
    @GET("v2/translate")
    suspend fun translate(
        @Body body: NetworkTranslationRequest
    ): NetworkTranslationResponse
}
