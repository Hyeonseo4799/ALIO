package com.skogkatt.network.model.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleListResponse(
    @SerialName("pages") val pages: Int,
    @SerialName("results") val articleResponses: List<ArticleResponse>,
)
