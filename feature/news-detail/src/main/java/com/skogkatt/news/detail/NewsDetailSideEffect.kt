package com.skogkatt.news.detail

sealed class NewsDetailSideEffect {
    data class ShowSnackbar(val error: String?) : NewsDetailSideEffect()
}
