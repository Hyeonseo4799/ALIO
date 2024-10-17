package com.skogkatt.news.detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.skogkatt.domain.GetTranslatedArticleContentUseCase
import com.skogkatt.domain.SynthesizeContentToFileUseCase
import com.skogkatt.model.article.ArticleWithBodyText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getTranslatedArticleContentUseCase: GetTranslatedArticleContentUseCase,
    private val synthesizeContentToFileUseCase: SynthesizeContentToFileUseCase,
    val exoPlayer: ExoPlayer,
) : ContainerHost<NewsDetailUiState, NewsDetailSideEffect>, ViewModel() {
    override val container = container<NewsDetailUiState, NewsDetailSideEffect>(NewsDetailUiState())

    fun refresh(id: String) = intent {
        reduce { state.copy(isLoading = true) }
        getTranslatedArticleContent(id)
    }

    private fun getTranslatedArticleContent(id: String) = intent {
        getTranslatedArticleContentUseCase(id)
            .onSuccess {
                reduce { state.copy(articleWithBodyText = it) }
                playSynthesizedAudio(it)
            }
            .onFailure {
                reduce { state.copy(error = it.message) }
                postSideEffect(NewsDetailSideEffect.ShowSnackbar(state.error))
            }
    }

    private fun playSynthesizedAudio(articleWithBodyText: ArticleWithBodyText) = intent {
        synthesizeContentToFileUseCase(articleWithBodyText)
            .onSuccess { files ->
                val mediaItems = files.map { file ->
                    val uri = Uri.fromFile(file)
                    MediaItem.fromUri(uri)
                }
                withContext(Dispatchers.Main) {
                    exoPlayer.setMediaItems(mediaItems)
                    exoPlayer.prepare()
                    exoPlayer.play()
                    reduce { state.copy(isLoading = false) }
                }
            }
            .onFailure {
                reduce { state.copy(error = it.message) }
                postSideEffect(NewsDetailSideEffect.ShowSnackbar(state.error))
            }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}
