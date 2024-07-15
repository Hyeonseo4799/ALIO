package com.skogkatt.network.api

import com.skogkatt.network.BuildConfig

internal enum class ApiType(
    val baseUrl: String,
) {
    GUARDIAN(BuildConfig.GUARDIAN_BASE_URL),
    DEEPL(BuildConfig.DEEPL_BASE_URL),
    GOOGLE_TTS(BuildConfig.GOOGLE_TTS_BASE_URL);

    internal fun getApiKey(): String = when (this) {
        GUARDIAN -> BuildConfig.GUARDIAN_API_KEY
        DEEPL -> BuildConfig.DEEPL_API_KEY
        GOOGLE_TTS -> BuildConfig.GOOGLE_TTS_API_KEY
    }
}
