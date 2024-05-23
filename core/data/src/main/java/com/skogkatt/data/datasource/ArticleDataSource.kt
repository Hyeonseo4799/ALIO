package com.skogkatt.data.datasource

import com.skogkatt.network.model.NetworkArticleResponse
import com.skogkatt.network.model.NetworkContentResponse

internal interface ArticleDataSource {
    suspend fun getAllArticles(page: Int): NetworkArticleResponse

    suspend fun getArticleContent(id: String): NetworkContentResponse
}
