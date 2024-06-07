package com.skogkatt.network.api

import com.skogkatt.network.BuildConfig

internal enum class ApiType(
    val baseUrl: String,
) {
    GUARDIAN(BuildConfig.GUARDIAN_BASE_URL),
    DEEPL(BuildConfig.DEEPL_BASE_URL),
    GOOGLE_TTS(BuildConfig.GOOGLE_TTS_BASE_URL),
}
