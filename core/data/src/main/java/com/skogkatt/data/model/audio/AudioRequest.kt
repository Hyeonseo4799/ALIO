package com.skogkatt.data.model.audio

import com.skogkatt.model.audio.AudioRequest
import com.skogkatt.network.model.audio.AudioConfig
import com.skogkatt.network.model.audio.Input
import com.skogkatt.network.model.audio.NetworkAudioRequest
import com.skogkatt.network.model.audio.Voice

internal fun AudioRequest.toNetworkAudioRequest() = NetworkAudioRequest(
    input = Input(text = text),
    voice = Voice(
        langCode = langCode,
        name = name
    ),
    audioConfig = AudioConfig(audioEncoding = audioEncoding),
)
