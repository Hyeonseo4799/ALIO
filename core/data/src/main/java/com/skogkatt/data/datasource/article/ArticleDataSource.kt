package com.skogkatt.data.datasource.article

import com.skogkatt.network.model.Response
import com.skogkatt.network.model.article.ArticleContentResponse
import com.skogkatt.network.model.article.ArticleListResponse
import com.skogkatt.network.model.article.EditorsPicksResponse

internal interface ArticleDataSource {
    suspend fun getArticles(page: Int, section: String?): Response<ArticleListResponse>

    suspend fun getArticleContent(id: String): Response<ArticleContentResponse>

    suspend fun getEditorsPicks(): Response<EditorsPicksResponse>
}
