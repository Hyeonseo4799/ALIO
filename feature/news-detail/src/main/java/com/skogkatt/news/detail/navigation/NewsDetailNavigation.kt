package com.skogkatt.news.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.skogkatt.navigation.Route
import com.skogkatt.news.detail.NewsDetailRoute

fun NavController.navigateToNewsDetail(id: String) {
    this.navigate(Route.NewsDetail(id))
}

fun NavGraphBuilder.newsDetailScreen(
    navigateToBack: () -> Unit,
) {
    composable<Route.NewsDetail> { navBackStackEntry ->
        val id = navBackStackEntry.toRoute<Route.NewsDetail>().id

        NewsDetailRoute(
            id = id,
            navigateToBack = navigateToBack
        )
    }
}