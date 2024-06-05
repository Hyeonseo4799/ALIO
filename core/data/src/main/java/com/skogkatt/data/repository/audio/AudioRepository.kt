package com.skogkatt.data.repository.audio

import com.skogkatt.model.audio.AudioRequest

internal interface AudioRepository {
    suspend fun synthesize(body: AudioRequest): String
}
