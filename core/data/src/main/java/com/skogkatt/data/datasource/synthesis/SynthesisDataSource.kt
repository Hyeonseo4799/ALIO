package com.skogkatt.data.datasource.synthesis

import com.skogkatt.network.model.synthesis.SynthesisRequest

internal interface SynthesisDataSource {
    suspend fun synthesize(body: SynthesisRequest): String
}
