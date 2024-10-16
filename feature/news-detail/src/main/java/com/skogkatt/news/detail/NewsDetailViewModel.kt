package com.skogkatt.news.detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.skogkatt.domain.GetTranslatedArticleContentUseCase
import com.skogkatt.domain.SynthesizeContentToFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getTranslatedArticleContentUseCase: GetTranslatedArticleContentUseCase,
    private val synthesizeContentToFileUseCase: SynthesizeContentToFileUseCase,
    private val exoPlayer: ExoPlayer,
) : ViewModel() {
    private val _newsDetailUiState = MutableStateFlow<NewsDetailUiState>(NewsDetailUiState.Loading)
    val newsDetailUiState: StateFlow<NewsDetailUiState> = _newsDetailUiState.asStateFlow()

    fun refresh(id: String) {
        viewModelScope.launch {
            playSynthesizedAudio(id)
            getTranslatedArticleContent(id)
        }
    }

    private suspend fun playSynthesizedAudio(id: String) {
        synthesizeContentToFileUseCase(id)
            .onSuccess { files ->
                val mediaItems = files.map {
                    val uri = Uri.fromFile(it)
                    MediaItem.fromUri(uri)
                }
                exoPlayer.setMediaItems(mediaItems)
                exoPlayer.prepare()
                exoPlayer.play()
            }
            .onFailure {
                _newsDetailUiState.value = NewsDetailUiState.Error(it.message)
            }
    }

    private suspend fun getTranslatedArticleContent(id: String) {
        getTranslatedArticleContentUseCase(id)
            .onSuccess {
                _newsDetailUiState.value = NewsDetailUiState.Success(it)
            }
            .onFailure {
                _newsDetailUiState.value = NewsDetailUiState.Error(it.message)
            }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}
