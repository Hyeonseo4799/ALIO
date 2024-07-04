package com.skogkatt.model.article

data class ArticleWithBodyText(
    val id: String,
    val sectionId: String,
    val publishedAt: String,
    val title: String,
    val thumbnailUrl: String,
    val bodyText: String,
)
