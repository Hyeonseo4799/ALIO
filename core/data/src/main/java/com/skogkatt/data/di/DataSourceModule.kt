package com.skogkatt.data.di

import com.skogkatt.data.datasource.ArticleDataSource
import com.skogkatt.data.datasource.ArticleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {
    @Binds
    fun bindsArticleDataSource(
        articleDataSourceImpl: ArticleDataSourceImpl,
    ): ArticleDataSource
}
