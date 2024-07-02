package com.skogkatt.network.api.retrofit

import com.skogkatt.network.api.Api
import com.skogkatt.network.api.ApiType
import com.skogkatt.network.model.article.NetworkArticleResponse
import com.skogkatt.network.model.article.NetworkContentResponse
import com.skogkatt.network.model.editorspicks.NetworkEditorsPicksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GuardianApi {
    @GET("search")
    @Api(ApiType.GUARDIAN)
    suspend fun getArticles(
        @Query("page") page: Int,
        @Query("q") query: String?,
        @Query("section") section: String?,
        @Query("show-fields") showFields: List<String> = listOf("thumbnail"),
    ): NetworkArticleResponse

    @GET("{id}")
    @Api(ApiType.GUARDIAN)
    suspend fun getArticleContent(
        @Path("id") id: String,
        @Query("show-fields") showFields: List<String> = listOf("bodyText", "thumbnail"),
    ): NetworkContentResponse

    @GET("world")
    @Api(ApiType.GUARDIAN)
    suspend fun getEditorsPicks(
        @Query("show-editors-picks") showEditorsPicks: Boolean = true,
        @Query("show-fields") showFields: List<String> = listOf("thumbnail")
    ): NetworkEditorsPicksResponse
}
