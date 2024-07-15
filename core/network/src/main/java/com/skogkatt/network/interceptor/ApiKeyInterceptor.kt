package com.skogkatt.network.interceptor

import com.skogkatt.network.api.Api
import com.skogkatt.network.api.ApiType
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.io.IOException

internal class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()

        val apiType = request.tag(Invocation::class.java)
            ?.method()
            ?.getAnnotation(Api::class.java)
            ?.apiType
            ?: throw IOException("API annotation not found for the request")

        val apiKey = apiType.getApiKey()

        when (apiType) {
            ApiType.GUARDIAN, ApiType.GOOGLE_TTS -> {
                val paramName = if (apiType == ApiType.GUARDIAN) "api-key" else "key"
                val newUrl = request.url.newBuilder()
                    .addQueryParameter(name = paramName, value = apiKey)
                    .build()

                newBuilder.url(newUrl)
            }

            ApiType.DEEPL -> newBuilder.addHeader(name = "Authorization", value = apiKey)
        }

        return chain.proceed(newBuilder.build())
    }
}