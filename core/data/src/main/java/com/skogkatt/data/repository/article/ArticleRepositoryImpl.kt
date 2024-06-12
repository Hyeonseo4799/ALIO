package com.skogkatt.data.repository.article

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.skogkatt.data.datasource.article.ArticleDataSource
import com.skogkatt.data.model.article.toArticleResponse
import com.skogkatt.data.model.article.toContentResponse
import com.skogkatt.data.paging.ArticlePagingSource
import com.skogkatt.model.article.ArticleResponse
import com.skogkatt.model.article.ContentResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ArticleRepositoryImpl @Inject constructor(
    private val articleDataSource: ArticleDataSource
): ArticleRepository {
    override fun getAllArticles(query: String?, section: String?): Flow<PagingData<ArticleResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArticlePagingSource { page ->
                    articleDataSource.getAllArticles(
                        page = page,
                        query = query,
                        section = section,
                    )
                }
            },
        ).flow.map { pagingData ->
            pagingData.map { it.toArticleResponse() }
        }
    }

    override suspend fun getArticleContent(id: String): ContentResponse {
        return articleDataSource.getArticleContent(id).content.toContentResponse()
    }
}
