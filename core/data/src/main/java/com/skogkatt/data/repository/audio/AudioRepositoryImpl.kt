package com.skogkatt.data.repository.audio

import com.skogkatt.data.datasource.audio.AudioDataSource
import com.skogkatt.data.model.audio.toNetworkAudioRequest
import com.skogkatt.model.audio.AudioRequest
import javax.inject.Inject

internal class AudioRepositoryImpl @Inject constructor(
    private val audioDataSource: AudioDataSource
): AudioRepository {
    override suspend fun synthesize(body: AudioRequest): String {
        return audioDataSource.synthesize(body.toNetworkAudioRequest())
    }
}
