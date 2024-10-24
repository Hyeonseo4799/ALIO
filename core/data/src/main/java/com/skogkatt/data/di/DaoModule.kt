package com.skogkatt.data.di

import com.skogkatt.data.datasource.local.article.dao.ArticleDao
import com.skogkatt.data.datasource.local.article.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providesArticleDao(
        database: ArticleDatabase
    ): ArticleDao = database.articleDao()
}
