package com.skogkatt.data.datasource.synthesis

import com.skogkatt.network.api.retrofit.GoogleTTSApi
import com.skogkatt.network.model.synthesis.SynthesisRequest
import javax.inject.Inject

internal class SynthesisDataSourceImpl @Inject constructor(
    private val googleTTSApi: GoogleTTSApi
): SynthesisDataSource {
    override suspend fun synthesize(body: SynthesisRequest): String {
        return googleTTSApi.synthesize(body)
    }
}
