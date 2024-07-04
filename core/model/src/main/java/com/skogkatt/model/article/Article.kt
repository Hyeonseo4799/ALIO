package com.skogkatt.model.article

data class Article(
    val id: String,
    val sectionId: String,
    val publishedAt: String,
    val title: String,
    val thumbnailUrl: String,
)
