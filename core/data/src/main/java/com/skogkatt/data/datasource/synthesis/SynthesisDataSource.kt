package com.skogkatt.data.datasource.synthesis

import com.skogkatt.network.model.synthesis.SynthesisRequest
import com.skogkatt.network.model.synthesis.SynthesisResponse

internal interface SynthesisDataSource {
    suspend fun synthesize(body: SynthesisRequest): SynthesisResponse
}
