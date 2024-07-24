package com.skogkatt.data.model.article

import com.skogkatt.data.util.toTimeAgo
import com.skogkatt.model.article.ArticleWithBodyText
import com.skogkatt.network.model.article.ArticleWithBodyTextResponse

internal fun ArticleWithBodyTextResponse.toArticleWithBodyText() = ArticleWithBodyText(
    id = id,
    sectionId = sectionId,
    publishedAt = publishedAt.toTimeAgo(),
    title = title,
    thumbnailUrl = fields.thumbnailUrl,
    bodyText = fields.bodyText,
)
