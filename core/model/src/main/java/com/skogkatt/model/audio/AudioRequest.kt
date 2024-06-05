package com.skogkatt.model.audio

data class AudioRequest(
    val text: String,
    val langCode: String,
    val name: String,
    val audioEncoding: String,
)
