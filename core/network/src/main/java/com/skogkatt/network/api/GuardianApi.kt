package com.skogkatt.network.api

import com.skogkatt.network.model.NetworkArticleResponse
import com.skogkatt.network.model.NetworkContentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianApi {
    @GET("search")
    suspend fun getAllArticles(
        @Query("page") page: Int,
    ): NetworkArticleResponse

    @GET
    suspend fun getArticleContent(
        @Query("id") id: String,
    ): NetworkContentResponse
}
