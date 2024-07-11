package com.skogkatt.news.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skogkatt.navigation.Route
import com.skogkatt.news.feed.NewsFeedRoute

fun NavGraphBuilder.newsFeedScreen(
    navigateToNewsDetail: (String) -> Unit,
) {
    composable<Route.NewsFeed> {
        NewsFeedRoute(navigateToNewsDetail = navigateToNewsDetail)
    }
}