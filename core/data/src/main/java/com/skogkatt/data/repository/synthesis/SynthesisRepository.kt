package com.skogkatt.data.repository.synthesis

import com.skogkatt.model.synthesis.Synthesis

interface SynthesisRepository {
    suspend fun synthesize(body: Synthesis): ByteArray
}
