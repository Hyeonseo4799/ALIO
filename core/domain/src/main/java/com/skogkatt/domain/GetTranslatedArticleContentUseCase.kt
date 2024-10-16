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
    suspend operator fun invoke(id: String): Result<ArticleWithBodyText> = runCatching {
        val articleContent = articleRepository.getArticleContent(id)
        val translation = Translation(listOf(articleContent.title, articleContent.bodyText))
        val (translatedTitle, translatedBodyText) = translationRepository.translate(translation)

        val regex = Regex("""\.(")|다\.""")

        articleContent.copy(
            title = translatedTitle,
            bodyText = translatedBodyText.replace(regex) {
                if (it.value == ".\"") ".\"\n\n" else "다.\n\n"
            }
        )
    }
}
