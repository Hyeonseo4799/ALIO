package com.skogkatt.news.detail

import com.skogkatt.model.article.ArticleWithBodyText

data class NewsDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val articleWithBodyText: ArticleWithBodyText? = null,
)
