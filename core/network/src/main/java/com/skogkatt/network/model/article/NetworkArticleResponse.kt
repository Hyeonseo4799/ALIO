package com.skogkatt.network.model.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticleResponse(
    @SerialName("pages") val pages: Int,
    @SerialName("results") val articles: List<NetworkArticle>,
)

@Serializable
data class NetworkArticle(
    val id: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val fields: Fields,
) {
    @Serializable
    data class Fields(
        val thumbnail: String,
    )
}
