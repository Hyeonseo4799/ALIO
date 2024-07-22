package com.skogkatt.domain

import com.skogkatt.data.repository.article.ArticleRepository
import com.skogkatt.data.repository.translation.TranslationRepository
import com.skogkatt.model.article.Article
import com.skogkatt.model.translation.Translation
import javax.inject.Inject

class GetTranslatedEditorsPicksUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val translationRepository: TranslationRepository,
) {
    suspend operator fun invoke(): Result<List<Article>> = runCatching {
        val editorsPicks = articleRepository.getEditorsPicks()
        val translation = Translation(editorsPicks.map(Article::title))
        val translatedTitles = translationRepository.translate(translation)

        editorsPicks.zip(translatedTitles) { editorsPick, translatedTitle ->
            editorsPick.copy(title = translatedTitle)
        }
    }
}