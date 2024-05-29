package com.skogkatt.data.model.article

import com.skogkatt.model.article.ContentResponse
import com.skogkatt.network.model.article.NetworkContent

internal fun NetworkContent.toContentResponse() = ContentResponse(
    id = id,
    type = type,
    sectionId = sectionId,
    sectionName = sectionName,
    webPublicationDate = webPublicationDate,
    webTitle = webTitle,
    webUrl = webUrl,
    fields = fields,
)
