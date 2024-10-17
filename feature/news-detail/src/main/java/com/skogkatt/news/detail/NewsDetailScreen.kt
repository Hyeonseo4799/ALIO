package com.skogkatt.news.detail

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.skogkatt.news.detail.component.HeaderImage
import com.skogkatt.ui.pretendard
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(UnstableApi::class)
@Composable
fun NewsDetailRoute(
    id: String,
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel = hiltViewModel(),
    showSnackbar: (String?) -> Unit,
) {
    val newsDetailUiState by viewModel.collectAsState()
    val scrollState = rememberLazyListState()
    var sentenceCount by remember { mutableIntStateOf(1) }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NewsDetailSideEffect.ShowSnackbar -> showSnackbar(sideEffect.error)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.refresh(id)

        viewModel.exoPlayer.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                sentenceCount += 1
            }
        })
    }

    LaunchedEffect(sentenceCount) {
        scrollState.animateScrollToItem(sentenceCount)
    }

    NewsDetailScreen(
        newsDetailUiState = newsDetailUiState,
        navigateToBack = navigateToBack,
        scrollState = scrollState,
        modifier = modifier,
    )
}

@Composable
internal fun NewsDetailScreen(
    newsDetailUiState: NewsDetailUiState,
    scrollState: LazyListState,
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val content = newsDetailUiState.articleWithBodyText

    if (newsDetailUiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    if (content != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color(0xFFF8F8F8)),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HeaderImage(
                    imageUrl = content.thumbnailUrl,
                    relativeTime = content.publishedAt,
                    navigateToBack = navigateToBack,
                )
            }

            item {
                Text(
                    text = content.title,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = pretendard,
                )
            }

            val paragraphs = content.bodyText.split("\n\n")

            items(paragraphs) { paragraph ->
                Text(
                    text = paragraph,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = pretendard,
                    lineHeight = 28.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewsDetailScreenPreview(
    @PreviewParameter(NewsDetailPreviewParameterProvider::class) newsDetailUiState: NewsDetailUiState
) {
    NewsDetailScreen(
        newsDetailUiState = newsDetailUiState,
        scrollState = rememberLazyListState(),
        navigateToBack = { },
    )
}
