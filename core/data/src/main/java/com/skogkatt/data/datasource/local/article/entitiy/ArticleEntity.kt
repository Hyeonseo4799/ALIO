package com.skogkatt.data.datasource.local.article.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("section_id")
    val sectionId: String,
    @ColumnInfo("published_at")
    val publishedAt: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("thumbnail_url")
    val thumbnailUrl: String,
    @ColumnInfo("body_text")
    val bodyText: String,
)
