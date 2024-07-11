package com.skogkatt.alio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.skogkatt.navigation.Route
import com.skogkatt.news.detail.navigation.navigateToNewsDetail
import com.skogkatt.news.detail.navigation.newsDetailScreen
import com.skogkatt.news.feed.navigation.newsFeedScreen

@Composable
internal fun ALIONavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Route = Route.NewsFeed,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        newsFeedScreen(navigateToNewsDetail = navController::navigateToNewsDetail)
        newsDetailScreen(navigateToBack = navController::popBackStack)
    }
}