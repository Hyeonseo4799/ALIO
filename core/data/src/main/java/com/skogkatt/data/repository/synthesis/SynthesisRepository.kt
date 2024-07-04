package com.skogkatt.data.repository.synthesis

import com.skogkatt.model.synthesis.Synthesis

internal interface SynthesisRepository {
    suspend fun synthesize(body: Synthesis): String
}
