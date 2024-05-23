package com.skogkatt.data.di

import com.skogkatt.data.repository.ArticleRepository
import com.skogkatt.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl,
    ): ArticleRepository
}
