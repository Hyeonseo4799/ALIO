package com.skogkatt.data.di

import android.content.Context
import androidx.room.Room
import com.skogkatt.data.datasource.local.article.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesArticleDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java,
        "article-database"
    ).build()
}
