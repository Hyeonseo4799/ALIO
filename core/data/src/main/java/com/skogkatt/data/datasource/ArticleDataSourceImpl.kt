package com.skogkatt.data.datasource

import com.skogkatt.network.api.GuardianApi
import com.skogkatt.network.model.NetworkArticleResponse
import com.skogkatt.network.model.NetworkContentResponse
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
