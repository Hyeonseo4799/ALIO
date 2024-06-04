package com.skogkatt.network.api

import com.skogkatt.network.model.audio.NetworkAudioRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleTTSApi {
    @POST("v1/text:synthesize")
    suspend fun synthesize(
        @Body body: NetworkAudioRequest,
    ): String
}
