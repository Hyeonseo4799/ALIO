package com.skogkatt.data.repository.article

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.skogkatt.data.datasource.article.ArticleDataSource
import com.skogkatt.data.datasource.local.article.dao.ArticleDao
import com.skogkatt.data.model.article.toArticle
import com.skogkatt.data.model.article.toArticleEntity
import com.skogkatt.data.model.article.toArticleWithBodyText
import com.skogkatt.data.paging.ArticlePagingSource
import com.skogkatt.model.article.Article
import com.skogkatt.model.article.ArticleWithBodyText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val articleDataSource: ArticleDataSource
) : ArticleRepository {
    override fun getArticles(section: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArticlePagingSource { page ->
                    articleDataSource.getArticles(
                        page = page,
                        section = section,
                    ).response
                }
            },
        ).flow.map { pagingData ->
            pagingData.map { it.toArticle() }
        }
    }

    override suspend fun getArticleContent(id: String): ArticleWithBodyText {
        val articleContent = articleDao.getArticleContent(id)?.toArticleWithBodyText()

        if (articleContent == null) {
            val articleWithBodyText = articleDataSource.getArticleContent(id).response.articleContent.toArticleWithBodyText()
            articleDao.insertArticle(articleWithBodyText.toArticleEntity())

            return articleWithBodyText
        }
        return articleContent
    }

    override suspend fun getEditorsPicks(): List<Article> {
        return articleDataSource.getEditorsPicks().response.editorsPicks.map { it.toArticle() }
    }
}
