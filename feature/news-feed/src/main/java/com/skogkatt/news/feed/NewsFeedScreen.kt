package com.skogkatt.news.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.skogkatt.model.article.ArticleResponse
import com.skogkatt.ui.pretendard
import kotlin.math.roundToInt

val AppBarHeight = 64.dp

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun NewsFeedScreen(
    editorPicks: List<ArticleResponse>,
    articles: List<ArticleResponse>,
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

    val pagerState = rememberPagerState(pageCount = { editorPicks.size })

    Box(
        modifier = Modifier
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
                    val editorPick = editorPicks[it]

                    EditorPicksCard(
                        title = editorPick.webTitle,
                        relativeTime = editorPick.webPublicationDate,
                        imageUrl = editorPick.thumbnail,
                        onClick = { /* TODO: 뉴스 디테일 화면으로 이동 */ },
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

            items(articles) { article ->
                NewsCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    title = article.webTitle,
                    relativeTime = article.webPublicationDate,
                    imageUrl = article.thumbnail,
                    onClick = { /* TODO: 뉴스 디테일 화면으로 이동 */ }
                )
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
            },
            actions = {
                Icon(
                    modifier = Modifier.padding(end = 20.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.Gray,
                )
            }
        )
    }
}

@Preview
@Composable
private fun NewsFeedScreenPreview() {
    val editorPicks = List(5) {
        ArticleResponse(
            id = "australia-news/article/2024/jun/18/reserve-bank-leaves-interest-rate-on-hold-at-435-with-borrowers-left-waiting-for-relief",
            sectionId = "australia-news",
            sectionName = "Australia News",
            webPublicationDate = "10분 전",
            webTitle = "중앙 은행은 대출자들이 구제를 기다리는 동안 금리를 4.35%로 동결합니다.",
            thumbnail = "https://media.guim.co.uk/fe3089b924e907625af3b3d3d82a7efae9f20cb7/0_41_3235_1941/500.jpg"
        )
    }

    val articles = List(10) {
        ArticleResponse(
            id = "business/article/2024/jun/18/investment-in-uk-has-trailed-other-g7-countries-since-mid-1990s-ippr-says",
            sectionId = "business",
            sectionName = "Business",
            webPublicationDate = "1시간 전",
            webTitle = "영국에 대한 투자는 1990 년대 중반 이후 다른 G7 국가를 뒤쫓고 있다고 IPPR은 말합니다.",
            thumbnail = "https://media.guim.co.uk/ae194759ab246c9f9d80ac7ec6209888b31dd133/0_373_5559_3337/500.jpg"
        )
    }

    NewsFeedScreen(editorPicks = editorPicks, articles = articles)
}

