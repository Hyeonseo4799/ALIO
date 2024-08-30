package com.skogkatt.news.detail

import com.skogkatt.model.article.ArticleWithBodyText

sealed interface NewsDetailUiState {
    data object Loading : NewsDetailUiState

    data class Error(val message: String?) : NewsDetailUiState

    data class Success(val articleWithBodyText: ArticleWithBodyText) : NewsDetailUiState
}