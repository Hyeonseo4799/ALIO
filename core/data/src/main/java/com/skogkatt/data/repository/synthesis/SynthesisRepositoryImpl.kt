package com.skogkatt.data.repository.synthesis

import com.skogkatt.data.datasource.synthesis.SynthesisDataSource
import com.skogkatt.data.model.synthesis.toSynthesisRequest
import com.skogkatt.model.synthesis.Synthesis
import javax.inject.Inject

internal class SynthesisRepositoryImpl @Inject constructor(
    private val synthesisDataSource: SynthesisDataSource
): SynthesisRepository {
    override suspend fun synthesize(body: Synthesis): String {
        return synthesisDataSource.synthesize(body.toSynthesisRequest())
    }
}
