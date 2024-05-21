package com.skogkatt.network.interceptor

import com.skogkatt.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        if (url.host.contains("content.guardianapis.com")) {
            val newUrl = url.newBuilder()
                .addQueryParameter(name = "api-key", value = BuildConfig.API_KEY)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(newRequest)
        }

        return chain.proceed(request)
    }
}
