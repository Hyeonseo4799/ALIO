package com.skogkatt.data.model.article

import com.skogkatt.data.util.toTimeAgo
import com.skogkatt.model.article.Article
import com.skogkatt.network.model.article.ArticleResponse

internal fun ArticleResponse.toArticle() = Article(
    id = id,
    sectionId = sectionId,
    publishedAt = publishedAt.toTimeAgo(),
    title = title,
    thumbnailUrl = fields.thumbnailUrl,
)
