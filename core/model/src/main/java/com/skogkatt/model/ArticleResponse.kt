package com.skogkatt.model

data class ArticleResponse(
    val pages: Int,
    val articles: List<Article>
)

data class Article(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
)
