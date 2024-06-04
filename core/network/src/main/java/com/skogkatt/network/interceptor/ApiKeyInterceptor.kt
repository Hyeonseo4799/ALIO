package com.skogkatt.network.interceptor

import com.skogkatt.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val host = request.url.host
        val builder = request.newBuilder()

        when {
            host.contains("content.guardianapis.com") -> {
                val newUrl = request.url.newBuilder()
                    .addQueryParameter(
                        name = "api-key",
                        value = BuildConfig.GUARDIAN_API_KEY
                    )
                    .build()

                builder.url(newUrl)
            }

            host.contains("api.deepl.com") -> {
                builder.addHeader(
                    name = "Authorization",
                    value = "DeepL-Auth-Key ${BuildConfig.DEEPL_API_KEY}"
                )
            }

            host.contains("texttospeech.googleapis.com") -> {
                val newUrl = request.url.newBuilder()
                    .addQueryParameter(
                        name = "key",
                        value = BuildConfig.GOOGLE_TTS_API_KEY
                    )
                    .build()

                builder.url(newUrl)
            }
        }

        return chain.proceed(builder.build())
    }
}
