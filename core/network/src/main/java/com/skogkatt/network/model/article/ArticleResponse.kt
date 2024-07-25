package com.skogkatt.network.model.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("id") val id: String,
    @SerialName("sectionId") val sectionId: String,
    @SerialName("webPublicationDate") val publishedAt: String,
    @SerialName("webTitle") val title: String,
    @SerialName("fields") val fields: Fields = Fields(),
) {
    @Serializable
    data class Fields(
        @SerialName("thumbnail") val thumbnailUrl: String = "",
    )
}

