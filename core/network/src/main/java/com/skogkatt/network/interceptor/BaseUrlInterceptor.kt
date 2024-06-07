package com.skogkatt.network.interceptor

import com.skogkatt.network.api.Api
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.io.IOException

internal class BaseUrlInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val api = request.tag(Invocation::class.java)
            ?.method()
            ?.getAnnotation(Api::class.java)
            ?: throw IOException("API annotation not found for the request")

        val baseUrl = api.apiType.baseUrl.toHttpUrl()

        val newUrl = request.url.newBuilder()
            .scheme(baseUrl.scheme)
            .host(baseUrl.host)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
