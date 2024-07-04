package com.skogkatt.data.datasource.article

import com.skogkatt.network.model.article.ArticleContentResponse
import com.skogkatt.network.model.article.ArticleListResponse
import com.skogkatt.network.model.article.EditorsPicksResponse

internal interface ArticleDataSource {
    suspend fun getArticles(page: Int, query: String?, section: String?): ArticleListResponse

    suspend fun getArticleContent(id: String): ArticleContentResponse

    suspend fun getEditorsPicks(): EditorsPicksResponse
}
