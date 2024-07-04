package com.skogkatt.data.di

import com.skogkatt.data.datasource.article.ArticleDataSource
import com.skogkatt.data.datasource.article.ArticleDataSourceImpl
import com.skogkatt.data.datasource.synthesis.SynthesisDataSource
import com.skogkatt.data.datasource.synthesis.SynthesisDataSourceImpl
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
        synthesisDataSourceImpl: SynthesisDataSourceImpl,
    ): SynthesisDataSource
}
