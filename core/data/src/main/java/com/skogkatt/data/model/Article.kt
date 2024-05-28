package com.skogkatt.data.model

import com.skogkatt.model.Article
import com.skogkatt.network.model.article.NetworkArticle

internal fun NetworkArticle.toArticle() = Article(
    id = id,
    type = type,
    sectionId = sectionId,
    sectionName = sectionName,
    webPublicationDate = webPublicationDate,
    webTitle = webTitle,
    webUrl = webUrl,
)
