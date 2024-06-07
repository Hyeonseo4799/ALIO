package com.skogkatt.network.di

import com.skogkatt.network.interceptor.ApiKeyInterceptor
import com.skogkatt.network.BuildConfig
import com.skogkatt.network.api.retrofit.DeepLApi
import com.skogkatt.network.api.retrofit.GoogleTTSApi
import com.skogkatt.network.api.retrofit.GuardianApi
import com.skogkatt.network.interceptor.BaseUrlInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        baseUrlInterceptor: BaseUrlInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(baseUrlInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GUARDIAN_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideGuardianApi(retrofit: Retrofit): GuardianApi {
        return retrofit.create(GuardianApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDeepLApi(retrofit: Retrofit): DeepLApi {
        return retrofit.create(DeepLApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleTTSApi(retrofit: Retrofit): GoogleTTSApi {
        return retrofit.create(GoogleTTSApi::class.java)
    }
}
