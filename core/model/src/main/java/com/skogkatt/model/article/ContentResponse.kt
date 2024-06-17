package com.skogkatt.model.article

data class ContentResponse(
    val id: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val thumbnail: String,
    val bodyText: String,
)
