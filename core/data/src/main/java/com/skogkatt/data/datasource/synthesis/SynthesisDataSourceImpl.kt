package com.skogkatt.data.datasource.synthesis

import com.skogkatt.network.api.retrofit.GoogleTTSApi
import com.skogkatt.network.model.synthesis.SynthesisRequest
import com.skogkatt.network.model.synthesis.SynthesisResponse
import javax.inject.Inject

internal class SynthesisDataSourceImpl @Inject constructor(
    private val googleTTSApi: GoogleTTSApi
) : SynthesisDataSource {
    override suspend fun synthesize(body: SynthesisRequest): SynthesisResponse {
        return googleTTSApi.synthesize(body)
    }
}
