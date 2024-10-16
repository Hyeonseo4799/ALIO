package com.skogkatt.domain

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// TODO: 추후 synthesizeLongAudio 로직으로 변경
class SynthesizeContentToFileUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val synthesizeArticleContentUseCase: SynthesizeArticleContentUseCase,
) {
//    suspend operator fun invoke(id: String, fileName: String = "audio.mp3"): Result<File> {
//        return synthesizeArticleContentUseCase(id).mapCatching { byteArray ->
//            val file = File(context.filesDir, fileName)
//            FileOutputStream(file).use { fos ->
//                fos.write(byteArray)
//            }
//            file
//        }
//    }
}
