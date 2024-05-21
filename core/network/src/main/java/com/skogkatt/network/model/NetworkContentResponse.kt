package com.skogkatt.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkContentResponse(
    @SerialName("response") val contents: List<NetworkContent>,
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
