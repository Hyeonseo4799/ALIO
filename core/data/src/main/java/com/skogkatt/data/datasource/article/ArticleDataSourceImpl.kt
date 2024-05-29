package com.skogkatt.data.datasource.article

import com.skogkatt.network.api.GuardianApi
import com.skogkatt.network.model.article.NetworkArticleResponse
import com.skogkatt.network.model.article.NetworkContentResponse
import javax.inject.Inject

internal class ArticleDataSourceImpl @Inject constructor(
    private val guardianApi: GuardianApi
): ArticleDataSource {
    override suspend fun getAllArticles(page: Int): NetworkArticleResponse {
        return guardianApi.getAllArticles(page)
    }

    override suspend fun getArticleContent(id: String): NetworkContentResponse {
        return guardianApi.getArticleContent(id)
    }
}
