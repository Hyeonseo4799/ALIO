package com.skogkatt.news.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.skogkatt.news.feed.component.EditorPicksCard
import com.skogkatt.news.feed.component.NewsCard
import com.skogkatt.ui.farnhamHeadline
import com.skogkatt.ui.pretendard
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import kotlin.math.roundToInt

private val AppBarHeight = 64.dp

@Composable
fun NewsFeedRoute(
    navigateToNewsDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsFeedViewModel = hiltViewModel(),
    showSnackbar: (String?) -> Unit,
) {
    val newsFeedUiState by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NewsFeedSideEffect.ShowSnackbar -> showSnackbar(sideEffect.error)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    NewsFeedScreen(
        newsFeedUiState = newsFeedUiState,
        navigateToNewsDetail = navigateToNewsDetail,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewsFeedScreen(
    newsFeedUiState: NewsFeedUiState,
    navigateToNewsDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val latestArticles = newsFeedUiState.latestArticles.collectAsLazyPagingItems()

    val appBarHeightPx = with(LocalDensity.current) { AppBarHeight.roundToPx().toFloat() }
    val appBarOffsetPx = remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = appBarOffsetPx.floatValue + delta
                appBarOffsetPx.floatValue = newOffset.coerceIn(-appBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    val pagerState = rememberPagerState(pageCount = { newsFeedUiState.editorsPicks.size })

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
            .background(color = Color(0xFFF8F8F8))
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = AppBarHeight),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text(
                    text = "에디터 추천",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 14.dp),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = pretendard,
                )
            }

            item {
                if (newsFeedUiState.editorsPicks.isEmpty()) {
                    Box(modifier = modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    pageSize = PageSize.Fill,
                    pageSpacing = 12.dp
                ) {
                    val editorsPick = newsFeedUiState.editorsPicks[it]

                    EditorPicksCard(
                        title = editorsPick.title,
                        relativeTime = editorsPick.publishedAt,
                        imageUrl = editorsPick.thumbnailUrl,
                        onClick = { navigateToNewsDetail(editorsPick.id) },
                    )
                }
            }

            item {
                Text(
                    text = "최근 뉴스",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = pretendard,
                )
            }

            item {
                if (latestArticles.itemCount == 0) {
                    Box(modifier = modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            items(
                count = latestArticles.itemCount,
                key = latestArticles.itemKey { it.id },
            ) {
                val latestArticle = latestArticles[it]

                if (latestArticle != null) {
                    NewsCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        title = latestArticle.title,
                        relativeTime = latestArticle.publishedAt,
                        imageUrl = latestArticle.thumbnailUrl,
                        onClick = { navigateToNewsDetail(latestArticle.id) }
                    )
                }
            }
        }

        CenterAlignedTopAppBar(
            modifier = Modifier
                .offset { IntOffset(x = 0, y = appBarOffsetPx.floatValue.roundToInt()) }
                .drawBehind {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(x = 0f, y = size.height),
                        end = Offset(x = size.width, y = size.height),
                        strokeWidth = 2f
                    )
                },
            title = {
                Text(
                    text = "alio",
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = farnhamHeadline,
                )
            }
        )
    }
}

@Preview
@Composable
private fun NewsFeedScreenPreview(
    @PreviewParameter(NewsFeedPreviewParameterProvider::class) newsFeedUiState: NewsFeedUiState,
) {
    NewsFeedScreen(
        newsFeedUiState = newsFeedUiState,
        navigateToNewsDetail = { },
    )
}
