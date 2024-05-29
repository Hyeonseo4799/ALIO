package com.skogkatt.network.model.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkContentResponse(
    @SerialName("response") val content: NetworkContent,
)

@Serializable
data class NetworkContent(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val fields: List<String>,
)
