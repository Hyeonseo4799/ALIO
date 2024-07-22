package com.skogkatt.news.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.skogkatt.domain.GetTranslatedArticlesUseCase
import com.skogkatt.domain.GetTranslatedEditorsPicksUseCase
import com.skogkatt.model.article.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val getTranslatedEditorsPicksUseCase: GetTranslatedEditorsPicksUseCase,
    private val getTranslatedArticlesUseCase: GetTranslatedArticlesUseCase,
) : ViewModel() {
    private val _newsFeedUiState = MutableStateFlow<NewsFeedUiState>(NewsFeedUiState.Loading)
    val newsFeedUiState: StateFlow<NewsFeedUiState> = _newsFeedUiState.asStateFlow()

    private val _latestArticles = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val latestArticles: Flow<PagingData<Article>> = _latestArticles

    fun refresh(section: String? = null) {
        viewModelScope.launch {
            getTranslatedEditorsPicks()
            getTranslatedArticles(section)
        }
    }

    private suspend fun getTranslatedEditorsPicks() {
        getTranslatedEditorsPicksUseCase()
            .onSuccess {
                _newsFeedUiState.value = NewsFeedUiState.Success(it)
            }
            .onFailure {
                _newsFeedUiState.value = NewsFeedUiState.Error(it.message)
            }
    }

    private suspend fun getTranslatedArticles(section: String?) {
        getTranslatedArticlesUseCase(section = section)
            .cachedIn(viewModelScope)
            .collect {
                _latestArticles.value = it
            }
    }
}