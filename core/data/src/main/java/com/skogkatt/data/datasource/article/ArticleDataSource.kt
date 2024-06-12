package com.skogkatt.data.datasource.article

import com.skogkatt.network.model.article.NetworkArticleResponse
import com.skogkatt.network.model.article.NetworkContentResponse

internal interface ArticleDataSource {
    suspend fun getArticles(page: Int, query: String?, section: String?): NetworkArticleResponse

    suspend fun getArticleContent(id: String): NetworkContentResponse
}
