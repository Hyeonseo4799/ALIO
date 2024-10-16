package com.skogkatt.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object NewsFeed : Route

    @Serializable
    data class NewsDetail(val id: String) : Route
}
