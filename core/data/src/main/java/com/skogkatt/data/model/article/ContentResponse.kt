package com.skogkatt.data.model.article

import com.skogkatt.model.article.ContentResponse
import com.skogkatt.network.model.article.NetworkContent

internal fun NetworkContent.toContentResponse() = ContentResponse(
    id = id,
    sectionId = sectionId,
    sectionName = sectionName,
    webTitle = webTitle,
    webPublicationDate = webPublicationDate,
    thumbnail = fields.thumbnail,
    bodyText = fields.bodyText,
)
