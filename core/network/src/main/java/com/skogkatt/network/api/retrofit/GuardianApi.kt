package com.skogkatt.network.api.retrofit

import com.skogkatt.network.api.Api
import com.skogkatt.network.api.ApiType
import com.skogkatt.network.model.article.NetworkArticleResponse
import com.skogkatt.network.model.article.NetworkContentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianApi {
    @GET("search")
    @Api(ApiType.GUARDIAN)
    suspend fun getAllArticles(
        @Query("page") page: Int,
        @Query("show-fields") showFields: List<String> = listOf("thumbnail")
    ): NetworkArticleResponse

    @GET
    @Api(ApiType.GUARDIAN)
    suspend fun getArticleContent(
        @Query("id") id: String,
        @Query("show-fields") showFields: List<String> = listOf("bodyText", "thumbnail")
    ): NetworkContentResponse
}
