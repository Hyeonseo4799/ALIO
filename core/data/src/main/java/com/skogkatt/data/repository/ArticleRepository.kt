package com.skogkatt.data.repository

import androidx.paging.PagingData
import com.skogkatt.model.Article
import com.skogkatt.model.Content
import kotlinx.coroutines.flow.Flow

internal interface ArticleRepository {
    fun getAllArticles(page: Int): Flow<PagingData<Article>>

    suspend fun getArticleContent(id: String): Content
}
