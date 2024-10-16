package com.skogkatt.news.detail

import androidx.lifecycle.ViewModel
import com.skogkatt.domain.GetTranslatedArticleContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getTranslatedArticleContentUseCase: GetTranslatedArticleContentUseCase,
//    private val synthesizeContentToFileUseCase: SynthesizeContentToFileUseCase,
//    private val exoPlayer: ExoPlayer,
) : ViewModel() {
    private val _newsDetailUiState = MutableStateFlow<NewsDetailUiState>(NewsDetailUiState.Loading)
    val newsDetailUiState: StateFlow<NewsDetailUiState> = _newsDetailUiState.asStateFlow()

    private val _audioFile = MutableStateFlow<File?>(null)
    val audioFile: StateFlow<File?> = _audioFile

//    TODO: 추후 synthesizeLongAudio 로직으로 변경
//    suspend fun playSynthesizedAudio(id: String) {
//        synthesizeContentToFileUseCase(id)
//            .onSuccess {
//                val uri = Uri.fromFile(it)
//                val mediaItem = MediaItem.fromUri(uri)
//                exoPlayer.setMediaItem(mediaItem)
//                exoPlayer.prepare()
//            }
//            .onFailure {
//                _newsDetailUiState.value = NewsDetailUiState.Error(it.message)
//            }
//    }

    suspend fun getTranslatedArticleContent(id: String) {
        getTranslatedArticleContentUseCase(id)
            .onSuccess {
                _newsDetailUiState.value = NewsDetailUiState.Success(it)
            }
            .onFailure {
                _newsDetailUiState.value = NewsDetailUiState.Error(it.message)
            }
    }

//    TODO: 추후 synthesizeLongAudio 로직으로 변경
//    override fun onCleared() {
//        super.onCleared()
//        exoPlayer.release()
//    }
}
