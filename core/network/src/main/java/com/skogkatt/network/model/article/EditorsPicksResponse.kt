package com.skogkatt.network.model.article

import kotlinx.serialization.Serializable

@Serializable
data class EditorsPicksResponse(
    val editorsPicks: List<ArticleResponse>
)
