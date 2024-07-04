package com.skogkatt.data.model.article

import com.skogkatt.model.article.Article
import com.skogkatt.network.model.article.ArticleResponse

internal fun ArticleResponse.toArticle() = Article(
    id = id,
    sectionId = sectionId,
    publishedAt = publishedAt,
    title = title,
    thumbnailUrl = fields.thumbnailUrl,
)
