package com.skogkatt.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.skogkatt.data.repository.article.ArticleRepository
import com.skogkatt.data.repository.translation.TranslationRepository
import com.skogkatt.model.article.Article
import com.skogkatt.model.translation.Translation
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTranslatedArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val translationRepository: TranslationRepository,
) {
    operator fun invoke(section: String?): Flow<PagingData<Article>> {
        return articleRepository.getArticles(section).map { pagingData ->
            pagingData.map { article ->
                coroutineScope {
                    val translationDeferred = async {
                        val translation = Translation(listOf(article.title))
                        translationRepository.translate(translation).joinToString()
                    }
                    article.copy(title = translationDeferred.await())
                }
            }
        }
    }
}
