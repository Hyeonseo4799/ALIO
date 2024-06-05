package com.skogkatt.data.datasource.audio

import com.skogkatt.network.model.audio.NetworkAudioRequest

internal interface AudioDataSource {
    suspend fun synthesize(body: NetworkAudioRequest): String
}
