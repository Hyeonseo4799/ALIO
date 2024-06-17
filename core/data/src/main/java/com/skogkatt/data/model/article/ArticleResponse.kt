package com.skogkatt.data.model.article

import com.skogkatt.model.article.ArticleResponse
import com.skogkatt.network.model.article.NetworkArticle

internal fun NetworkArticle.toArticleResponse() = ArticleResponse(
    id = id,
    sectionId = sectionId,
    sectionName = sectionName,
    webPublicationDate = webPublicationDate,
    webTitle = webTitle,
    thumbnail = fields.thumbnail,
)
