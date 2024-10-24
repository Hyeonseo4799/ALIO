package com.skogkatt.data.datasource.local.article.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skogkatt.data.datasource.local.article.dao.ArticleDao
import com.skogkatt.data.datasource.local.article.entitiy.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
