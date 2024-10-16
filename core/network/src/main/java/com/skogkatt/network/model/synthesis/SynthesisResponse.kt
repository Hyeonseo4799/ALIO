package com.skogkatt.network.model.synthesis

import kotlinx.serialization.Serializable

@Serializable
data class SynthesisResponse(
    val audioContent: String,
)
