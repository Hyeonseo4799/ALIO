package com.skogkatt.network.api.retrofit

import com.skogkatt.network.api.Api
import com.skogkatt.network.api.ApiType
import com.skogkatt.network.model.Response
import com.skogkatt.network.model.article.ArticleContentResponse
import com.skogkatt.network.model.article.ArticleListResponse
import com.skogkatt.network.model.article.EditorsPicksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GuardianApi {
    @GET("search")
    @Api(ApiType.GUARDIAN)
    suspend fun getArticles(
        @Query("page") page: Int,
        @Query("section") section: String?,
        @Query("show-fields") showFields: String = "thumbnail",
        @Query("type") type: String = "article",
    ): Response<ArticleListResponse>

    @GET("{id}")
    @Api(ApiType.GUARDIAN)
    suspend fun getArticleById(
        @Path("id") id: String,
        @Query("show-fields") showFields: String = "bodyText,thumbnail",
    ): Response<ArticleContentResponse>

    @GET("world")
    @Api(ApiType.GUARDIAN)
    suspend fun getEditorsPicks(
        @Query("show-editors-picks") showEditorsPicks: Boolean = true,
        @Query("show-fields") showFields: String = "thumbnail",
        @Query("type") type: String = "article",
    ): Response<EditorsPicksResponse>
}
