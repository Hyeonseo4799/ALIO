package com.skogkatt.network.model.article

import kotlinx.serialization.Serializable

@Serializable
data class NetworkContentResponse(
    val content: NetworkContent,
)

@Serializable
data class NetworkContent(
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
        val bodyText: String,
    )
}
