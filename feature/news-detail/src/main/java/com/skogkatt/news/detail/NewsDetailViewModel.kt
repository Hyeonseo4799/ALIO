package com.skogkatt.news.detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.skogkatt.domain.GetTranslatedArticleContentUseCase
import com.skogkatt.domain.SynthesizeContentToFileUseCase
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
        playSynthesizedAudio(id)
        getTranslatedArticleContent(id)
    }

    private fun playSynthesizedAudio(id: String) = intent {
        synthesizeContentToFileUseCase(id)
            .onSuccess { files ->
                val mediaItems = files.map {
                    val uri = Uri.fromFile(it)
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

    private fun getTranslatedArticleContent(id: String) = intent {
        getTranslatedArticleContentUseCase(id)
            .onSuccess {
                reduce { state.copy(articleWithBodyText = it) }
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
