package com.skogkatt.domain

import android.content.Context
import com.skogkatt.model.article.ArticleWithBodyText
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class SynthesizeContentToFileUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val synthesizeArticleContentUseCase: SynthesizeArticleContentUseCase,
) {
    suspend operator fun invoke(
        articleWithBodyText: ArticleWithBodyText,
        fileName: String = "audio.mp3"
    ): Result<List<File>> {
        return synthesizeArticleContentUseCase(articleWithBodyText).mapCatching { byteArrays ->
            byteArrays.mapIndexed { index, byteArray ->
                val file = File(context.filesDir, "$fileName$index")
                FileOutputStream(file).use { fos ->
                    fos.write(byteArray)
                }
                file
            }
        }
    }
}
