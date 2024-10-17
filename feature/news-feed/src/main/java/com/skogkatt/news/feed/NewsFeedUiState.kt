package com.skogkatt.news.feed

import androidx.paging.PagingData
import com.skogkatt.model.article.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class NewsFeedUiState(
    val editorsPicks: List<Article> = emptyList(),
    val latestArticles: Flow<PagingData<Article>> = flow { PagingData.empty<Article>() },
    val error: String? = null,
)
