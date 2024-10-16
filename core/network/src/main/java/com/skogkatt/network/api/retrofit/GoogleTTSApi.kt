package com.skogkatt.network.api.retrofit

import com.skogkatt.network.api.Api
import com.skogkatt.network.api.ApiType
import com.skogkatt.network.model.synthesis.SynthesisRequest
import com.skogkatt.network.model.synthesis.SynthesisResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleTTSApi {
    @POST("v1/text:synthesize")
    @Api(ApiType.GOOGLE_TTS)
    suspend fun synthesize(
        @Body body: SynthesisRequest,
    ): SynthesisResponse
}
