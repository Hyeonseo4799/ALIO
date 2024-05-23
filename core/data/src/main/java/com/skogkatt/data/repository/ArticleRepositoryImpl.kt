package com.skogkatt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.skogkatt.data.datasource.ArticleDataSource
import com.skogkatt.data.model.toArticle
import com.skogkatt.data.model.toContent
import com.skogkatt.data.paging.ArticlePagingSource
import com.skogkatt.model.Article
import com.skogkatt.model.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ArticleRepositoryImpl @Inject constructor(
    private val articleDataSource: ArticleDataSource
): ArticleRepository {
    override fun getAllArticles(page: Int): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArticlePagingSource(articleDataSource::getAllArticles)
            },
        ).flow.map { pagingData ->
            pagingData.map { it.toArticle() }
        }
    }

    override suspend fun getArticleContent(id: String): Content {
        return articleDataSource.getArticleContent(id).content.toContent()
    }
}
