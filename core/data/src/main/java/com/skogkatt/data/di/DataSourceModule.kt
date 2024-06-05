package com.skogkatt.data.di

import com.skogkatt.data.datasource.article.ArticleDataSource
import com.skogkatt.data.datasource.article.ArticleDataSourceImpl
import com.skogkatt.data.datasource.audio.AudioDataSource
import com.skogkatt.data.datasource.audio.AudioDataSourceImpl
import com.skogkatt.data.datasource.translation.TranslationDataSource
import com.skogkatt.data.datasource.translation.TranslationDataSourceImpl
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

    @Binds
    fun bindsTranslationDataSource(
        translationDataSourceImpl: TranslationDataSourceImpl,
    ): TranslationDataSource

    @Binds
    fun bindsAudioDataSource(
        audioDataSourceImpl: AudioDataSourceImpl,
    ): AudioDataSource
}
