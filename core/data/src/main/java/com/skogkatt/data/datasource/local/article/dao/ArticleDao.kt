package com.skogkatt.data.datasource.local.article.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skogkatt.data.datasource.local.article.entitiy.ArticleEntity

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE id = :id")
    fun getArticleContent(id: String): ArticleEntity?

    @Insert
    fun insertArticle(articleEntity: ArticleEntity)

    @Delete
    fun deleteArticle(articleEntity: ArticleEntity)
}
