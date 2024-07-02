package com.skogkatt.network.model.editorspicks

import com.skogkatt.network.model.article.NetworkArticle

data class NetworkEditorsPicksResponse(
    val editorsPicks: List<NetworkArticle>,
)