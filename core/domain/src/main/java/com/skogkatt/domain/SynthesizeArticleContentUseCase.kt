package com.skogkatt.domain

import com.skogkatt.data.repository.synthesis.SynthesisRepository
import com.skogkatt.model.synthesis.Synthesis
import javax.inject.Inject

class SynthesizeArticleContentUseCase @Inject constructor(
    private val synthesisRepository: SynthesisRepository,
    private val getTranslatedArticleContentUseCase: GetTranslatedArticleContentUseCase,
) {
    suspend operator fun invoke(id: String, voice: String = "ko-KR-Neural2-B"): ByteArray {
        val articleContent = getTranslatedArticleContentUseCase(id)
        val synthesis = Synthesis(text = articleContent.bodyText, voice = voice)

        return synthesisRepository.synthesize(synthesis)
    }
}