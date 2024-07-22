package com.skogkatt.news.feed

import com.skogkatt.model.article.Article

sealed interface NewsFeedUiState {
    data object Loading : NewsFeedUiState

    data class Error(val message: String?) : NewsFeedUiState

    data class Success(val editorsPicks: List<Article>) : NewsFeedUiState
}