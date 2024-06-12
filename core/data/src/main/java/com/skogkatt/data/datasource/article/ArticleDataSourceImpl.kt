package com.skogkatt.data.datasource.article

import com.skogkatt.network.api.retrofit.GuardianApi
import com.skogkatt.network.model.article.NetworkArticleResponse
import com.skogkatt.network.model.article.NetworkContentResponse
import javax.inject.Inject

internal class ArticleDataSourceImpl @Inject constructor(
    private val guardianApi: GuardianApi
) : ArticleDataSource {
    override suspend fun getArticles(
        page: Int,
        query: String?,
        section: String?,
    ): NetworkArticleResponse {
        return guardianApi.getArticles(
            page = page,
            query = query,
            section = section
        )
    }

    override suspend fun getArticleContent(id: String): NetworkContentResponse {
        return guardianApi.getArticleContent(id)
    }
}
