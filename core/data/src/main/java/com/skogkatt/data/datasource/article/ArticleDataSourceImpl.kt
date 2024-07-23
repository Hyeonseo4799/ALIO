package com.skogkatt.data.datasource.article

import com.skogkatt.network.api.retrofit.GuardianApi
import com.skogkatt.network.model.Response
import com.skogkatt.network.model.article.ArticleContentResponse
import com.skogkatt.network.model.article.ArticleListResponse
import com.skogkatt.network.model.article.EditorsPicksResponse
import javax.inject.Inject

internal class ArticleDataSourceImpl @Inject constructor(
    private val guardianApi: GuardianApi
) : ArticleDataSource {
    override suspend fun getArticles(
        page: Int,
        section: String?,
    ): Response<ArticleListResponse> {
        return guardianApi.getArticles(
            page = page,
            section = section
        )
    }

    override suspend fun getArticleContent(id: String): Response<ArticleContentResponse> {
        return guardianApi.getArticleById(id)
    }

    override suspend fun getEditorsPicks(): Response<EditorsPicksResponse> {
        return guardianApi.getEditorsPicks()
    }
}
