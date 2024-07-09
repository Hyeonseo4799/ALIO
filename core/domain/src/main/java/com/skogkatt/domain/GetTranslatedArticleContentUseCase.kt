package com.skogkatt.domain

import com.skogkatt.data.repository.article.ArticleRepository
import com.skogkatt.data.repository.translation.TranslationRepository
import com.skogkatt.model.article.ArticleWithBodyText
import com.skogkatt.model.translation.Translation
import javax.inject.Inject

class GetTranslatedArticleContentUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val translationRepository: TranslationRepository,
) {
    suspend operator fun invoke(id: String): ArticleWithBodyText {
        val articleContent = articleRepository.getArticleContent(id)
        val translation = Translation(listOf(articleContent.bodyText))
        val translatedBodyText = translationRepository.translate(translation).joinToString()

        return articleContent.copy(bodyText = translatedBodyText)
    }
}