package com.skogkatt.data.datasource.audio

import com.skogkatt.network.api.retrofit.GoogleTTSApi
import com.skogkatt.network.model.audio.NetworkAudioRequest
import javax.inject.Inject

internal class AudioDataSourceImpl @Inject constructor(
    private val googleTTSApi: GoogleTTSApi
): AudioDataSource {
    override suspend fun synthesize(body: NetworkAudioRequest): String {
        return googleTTSApi.synthesize(body)
    }
}
