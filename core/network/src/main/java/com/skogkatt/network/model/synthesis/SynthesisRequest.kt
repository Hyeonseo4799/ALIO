package com.skogkatt.network.model.synthesis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: 추후 synthesizeLongAudio 로직으로 변경
@Serializable
data class SynthesisRequest(
    val input: Input,
    val voice: Voice,
    val audioConfig: AudioConfig,
)

@Serializable
data class Input(
    val text: String,
)

@Serializable
data class Voice(
    @SerialName("languageCode") val langCode: String,
    @SerialName("name") val name: String,
)

@Serializable
data class AudioConfig(
    val audioEncoding: String,
)
