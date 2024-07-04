package com.skogkatt.data.repository.article

import androidx.paging.PagingData
import com.skogkatt.model.article.Article
import com.skogkatt.model.article.ArticleWithBodyText
import kotlinx.coroutines.flow.Flow

internal interface ArticleRepository {
    fun getArticles(section: String?): Flow<PagingData<Article>>

    suspend fun getArticleContent(id: String): ArticleWithBodyText

    suspend fun getEditorsPicks(): List<Article>
}
