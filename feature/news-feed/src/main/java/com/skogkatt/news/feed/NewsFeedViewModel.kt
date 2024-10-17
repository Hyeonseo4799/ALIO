package com.skogkatt.news.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skogkatt.domain.GetTranslatedArticlesUseCase
import com.skogkatt.domain.GetTranslatedEditorsPicksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val getTranslatedEditorsPicksUseCase: GetTranslatedEditorsPicksUseCase,
    private val getTranslatedArticlesUseCase: GetTranslatedArticlesUseCase,
) : ContainerHost<NewsFeedUiState, NewsFeedSideEffect>, ViewModel() {
    override val container = container<NewsFeedUiState, NewsFeedSideEffect>(NewsFeedUiState())

    fun refresh(section: String? = null) = intent {
        getTranslatedEditorsPicks()
        getTranslatedArticles(section)
    }

    private fun getTranslatedEditorsPicks() = intent {
        getTranslatedEditorsPicksUseCase()
            .onSuccess {
                reduce { state.copy(editorsPicks = it) }
            }
            .onFailure {
                reduce { state.copy(error = it.message) }
                postSideEffect(NewsFeedSideEffect.ShowSnackbar(state.error))
            }
    }

    private fun getTranslatedArticles(section: String?) = intent {
        val latestArticles = getTranslatedArticlesUseCase(section = section)
            .cachedIn(viewModelScope)
            .catch {
                reduce { state.copy(error = it.message) }
                postSideEffect(NewsFeedSideEffect.ShowSnackbar(state.error))
            }
        reduce { state.copy(latestArticles = latestArticles) }
    }
}
