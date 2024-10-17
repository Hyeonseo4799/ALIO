package com.skogkatt.domain

import com.skogkatt.data.repository.synthesis.SynthesisRepository
import com.skogkatt.model.synthesis.Synthesis
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SynthesizeArticleContentUseCase @Inject constructor(
    private val synthesisRepository: SynthesisRepository,
    private val getTranslatedArticleContentUseCase: GetTranslatedArticleContentUseCase,
) {
    suspend operator fun invoke(id: String, voice: String = "ko-KR-Neural2-B"): Result<List<ByteArray>> {
        return getTranslatedArticleContentUseCase(id).mapCatching { articleContent ->
            val texts = articleContent.bodyText.split("\n\n")
            val results = coroutineScope {
                texts.map { text ->
                    val synthesis = Synthesis(text = text, voice = voice)
                    async { synthesisRepository.synthesize(synthesis) }
                }
            }
            results.awaitAll()
        }
    }
}
