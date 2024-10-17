package com.skogkatt.domain

import com.skogkatt.data.repository.synthesis.SynthesisRepository
import com.skogkatt.model.article.ArticleWithBodyText
import com.skogkatt.model.synthesis.Synthesis
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SynthesizeArticleContentUseCase @Inject constructor(
    private val synthesisRepository: SynthesisRepository,
) {
    suspend operator fun invoke(
        articleWithBodyText: ArticleWithBodyText,
        voice: String = "ko-KR-Neural2-B"
    ): Result<List<ByteArray>> = runCatching {
//        return getTranslatedArticleContentUseCase(id).mapCatching { articleContent ->
            val texts = articleWithBodyText.bodyText.split("\n\n")
            val results = coroutineScope {
                texts.map { text ->
                    val synthesis = Synthesis(text = text, voice = voice)
                    async { synthesisRepository.synthesize(synthesis) }
                }
            }
            results.awaitAll()
//        }
    }
}
