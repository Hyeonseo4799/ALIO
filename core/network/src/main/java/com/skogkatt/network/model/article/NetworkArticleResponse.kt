package com.skogkatt.network.model.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticleResponse(
    @SerialName("pages") val pages: Int,
    @SerialName("results") val articles: List<NetworkArticle>,
)
