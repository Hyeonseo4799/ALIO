package com.skogkatt.news.feed

sealed class NewsFeedSideEffect {
    data class ShowSnackbar(val error: String?) : NewsFeedSideEffect()
    data class OnNewsClicked(val id: String): NewsFeedSideEffect()
}
