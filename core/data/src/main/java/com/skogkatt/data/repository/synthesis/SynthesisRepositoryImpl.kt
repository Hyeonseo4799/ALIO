package com.skogkatt.data.repository.synthesis

import android.util.Base64
import com.skogkatt.data.datasource.synthesis.SynthesisDataSource
import com.skogkatt.data.model.synthesis.toSynthesisRequest
import com.skogkatt.model.synthesis.Synthesis
import javax.inject.Inject

internal class SynthesisRepositoryImpl @Inject constructor(
    private val synthesisDataSource: SynthesisDataSource
) : SynthesisRepository {
    override suspend fun synthesize(body: Synthesis): ByteArray {
        val result = synthesisDataSource.synthesize(body.toSynthesisRequest())
        
        return Base64.decode(result, Base64.DEFAULT)
    }
}
