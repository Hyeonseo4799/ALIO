package com.skogkatt.data.model.synthesis

import com.skogkatt.model.synthesis.Synthesis
import com.skogkatt.network.model.synthesis.AudioConfig
import com.skogkatt.network.model.synthesis.Input
import com.skogkatt.network.model.synthesis.SynthesisRequest
import com.skogkatt.network.model.synthesis.Voice

internal fun Synthesis.toSynthesisRequest() = SynthesisRequest(
    input = Input(text = text),
    voice = Voice(langCode = "ko-KR", name = voice),
    audioConfig = AudioConfig(audioEncoding = "MP3"),
)
