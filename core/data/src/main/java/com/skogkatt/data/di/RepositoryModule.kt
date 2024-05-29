package com.skogkatt.data.di

import com.skogkatt.data.repository.article.ArticleRepository
import com.skogkatt.data.repository.article.ArticleRepositoryImpl
import com.skogkatt.data.repository.translation.TranslationRepository
import com.skogkatt.data.repository.translation.TranslationRepositoryImpl
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

    @Binds
    fun bindsTranslationRepository(
        translationRepositoryImpl: TranslationRepositoryImpl,
    ): TranslationRepository
}
