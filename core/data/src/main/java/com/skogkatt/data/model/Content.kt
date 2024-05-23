package com.skogkatt.data.model

import com.skogkatt.model.Content
import com.skogkatt.network.model.NetworkContent

internal fun NetworkContent.toContent() = Content(
    id = id,
    type = type,
    sectionId = sectionId,
    sectionName = sectionName,
    webPublicationDate = webPublicationDate,
    webTitle = webTitle,
    webUrl = webUrl,
    fields = fields,
)
