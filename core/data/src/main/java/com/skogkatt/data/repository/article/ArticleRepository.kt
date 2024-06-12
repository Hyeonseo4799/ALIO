package com.skogkatt.data.repository.article

import androidx.paging.PagingData
import com.skogkatt.model.article.ArticleResponse
import com.skogkatt.model.article.ContentResponse
import kotlinx.coroutines.flow.Flow

internal interface ArticleRepository {
    fun getArticles(query: String?, section: String?): Flow<PagingData<ArticleResponse>>

    suspend fun getArticleContent(id: String): ContentResponse
}
