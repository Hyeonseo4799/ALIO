package com.skogkatt.network.di

import com.skogkatt.network.interceptor.ApiKeyInterceptor
import com.skogkatt.network.BuildConfig
import com.skogkatt.network.api.DeepLApi
import com.skogkatt.network.api.GuardianApi
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
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
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
}
