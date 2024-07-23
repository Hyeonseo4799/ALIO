package com.skogkatt.network.model.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleContentResponse(
    @SerialName("content") val articleContent: ArticleWithBodyTextResponse,
)

@Serializable
data class ArticleWithBodyTextResponse(
    @SerialName("id") val id: String,
    @SerialName("sectionId") val sectionId: String,
    @SerialName("webPublicationDate") val publishedAt: String,
    @SerialName("webTitle") val title: String,
    @SerialName("fields") val fields: Fields,
) {
    @Serializable
    data class Fields(
        @SerialName("thumbnail") val thumbnailUrl: String,
        @SerialName("bodyText") val bodyText: String,
    )
}
