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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.skogkatt.model.article.Article
import com.skogkatt.ui.pretendard
import kotlinx.coroutines.flow.flowOf
import kotlin.math.roundToInt

private val AppBarHeight = 64.dp

@Composable
fun NewsFeedRoute(
    navigateToNewsDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val newsFeedUiState by viewModel.newsFeedUiState.collectAsStateWithLifecycle()
    val latestArticles = viewModel.latestArticles.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    NewsFeedScreen(
        newsFeedUiState = newsFeedUiState,
        latestArticles = latestArticles,
        navigateToNewsDetail = navigateToNewsDetail,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewsFeedScreen(
    newsFeedUiState: NewsFeedUiState,
    latestArticles: LazyPagingItems<Article>,
    navigateToNewsDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
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

    when (newsFeedUiState) {
        is NewsFeedUiState.Success -> {
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
                        // TODO: 아이콘 또는 텍스트 로고로 변경
                        Text(
                            text = "ALIO",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = pretendard,
                        )
                    }
                )
            }
        }

        is NewsFeedUiState.Error -> { /* TODO: 에러 상태 처리 */ }

        NewsFeedUiState.Loading -> { /* TODO: Loading indicator 표시 */ }
    }

}

@Preview
@Composable
private fun NewsFeedScreenPreview() {
    val editorsPicks = List(5) {
        Article(
            id = "australia-news/article/2024/jun/18/reserve-bank-leaves-interest-rate-on-hold-at-435-with-borrowers-left-waiting-for-relief",
            sectionId = "australia-news",
            publishedAt = "10분 전",
            title = "중앙 은행은 대출자들이 구제를 기다리는 동안 금리를 4.35%로 동결합니다.",
            thumbnailUrl = "https://media.guim.co.uk/fe3089b924e907625af3b3d3d82a7efae9f20cb7/0_41_3235_1941/500.jpg"
        )
    }

    val articles = List(10) {
        Article(
            id = "business/article/2024/jun/18/investment-in-uk-has-trailed-other-g7-countries-since-mid-1990s-ippr-says/$it",
            sectionId = "business",
            publishedAt = "1시간 전",
            title = "영국에 대한 투자는 1990 년대 중반 이후 다른 G7 국가를 뒤쫓고 있다고 IPPR은 말합니다.",
            thumbnailUrl = "https://media.guim.co.uk/ae194759ab246c9f9d80ac7ec6209888b31dd133/0_373_5559_3337/500.jpg"
        )
    }

    NewsFeedScreen(
        newsFeedUiState = NewsFeedUiState.Success(editorsPicks = editorsPicks),
        latestArticles = flowOf(PagingData.from(articles)).collectAsLazyPagingItems(),
        navigateToNewsDetail = { },
    )
}

