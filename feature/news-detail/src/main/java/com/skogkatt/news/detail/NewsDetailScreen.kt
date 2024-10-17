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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.skogkatt.model.article.ArticleWithBodyText
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
    navigateToBack: () -> Unit,
    scrollState: LazyListState,
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
private fun NewsDetailScreenPreview() {
    val content = ArticleWithBodyText(
        id = "australia-news/article/2024/jun/18/reserve-bank-leaves-interest-rate-on-hold-at-435-with-borrowers-left-waiting-for-relief",
        sectionId = "australia-news",
        publishedAt = "10분 전",
        title = "중앙 은행은 대출자들이 구제를 기다리는 동안 금리를 4.35%로 동결합니다.",
        thumbnailUrl = "https://media.guim.co.uk/fe3089b924e907625af3b3d3d82a7efae9f20cb7/0_41_3235_1941/500.jpg",
        bodyText = """
            호주 중앙은행이 5회 연속 이사회에서 기준금리를 동결했지만 필요한 경우 추가 금리 인상 옵션을 남겨두면서 호주 대출자들은 또 한 번 유예를 받았습니다. 
            
            경제학자들의 예상대로 RBA는 화요일 오후에 끝난 이틀간의 회의에서 현금 금리를 12년 만에 최고치인 4.35%로 동결했습니다. 

            하지만 은행의 성명서는 "인플레이션이 목표치를 상회하고 있으며 지속적임을 입증하고 있다"고 경고했습니다.

            "인플레이션은 완화되고 있지만 이전에 예상했던 것보다 더 느리게 완화되고 있으며 여전히 높은 수준입니다."라고 성명에서 말했습니다.

            "합리적인 기간 내에 인플레이션을 목표치로 되돌릴 수 있는 금리의 경로는 여전히 불확실하며, 위원회는 어떤 것도 결정하지 않고 있다"며 필요한 경우 또 다른 금리 인상 옵션을 남겨둔다는 원론을 반복했습니다. 

            RateCity에 따르면 일반적인 60만 달러 대출을 받은 모기지 보유자는 2022년 5월 중앙은행이 13번의 금리 인상 주기를 시작한 이후 매달 약 1,450달러를 더 갚고 있습니다.

            3월 분기 인플레이션이 예상보다 둔화되면서 2024년 말 이전에 금리가 인하될 가능성은 희미해졌습니다. 

            RBA는 심지어 이번 분기에 소비자 물가 상승이 가속화되다가 다시 하락세로 돌아설 것으로 예측하고 있습니다. 

            완고한 인플레이션은 올해 초 경제가 거의 정체된 상황에서도 발생했습니다.

            그럼에도 불구하고 실업률이 약 4%에 머물면서 고용 증가세가 놀라울 정도로 견고하게 유지되고 있어 가계가 생활비 상승을 감당하는 데 도움이 되고 있습니다. 

            7월 1일부터 3단계 세금 감면이 시작되고 분기별로 주택당 75달러의 에너지 환급이 시작되면 더 많은 지원이 이루어질 것입니다. 

            그러나 RBA는 저축 감소를 포함한 추가 지출이 과도한 것으로 판명되어 다음 조치가 금리 인하가 아닌 금리 인상으로 이어질지 지켜볼 것입니다.

            즉각적인 시장 반응은 호주 달러가 약 0.1센트 강세인 66.2센트로 소폭 상승하는 데 그쳤습니다. 주가도 잠시 상승폭을 일부 반납한 후 1% 상승세로 마감했습니다.

            RBA는 수요와 공급 간의 균형을 회복하기 위해 노력해 왔습니다.

            그러나 현재로서는 "광범위한 데이터는 노동 및 비 노동 투입 모두에 대한 국내 비용 압력 상승과 함께 경제의 지속적인 초과 수요를 나타냅니다."라고 중앙 은행은 말했습니다. 

            이어진 기자회견에서 미셸 불록 RBA 총재는 이사회가 금리 인상과 함께 금리 동결 옵션을 고려하고 있다고 확인했습니다. 

            그녀는 "인플레이션을 낮추기 위해 천천히 갈 것"이라고 말하며 "2025년 말까지 인플레이션이 [2%~3% 목표] 밴드로 다시 내려갈 것"이라고 전망했습니다.

            불록 총재는 경기 침체를 유발하지 않고 인플레이션을 낮추는 "여전히 좁은 길에 있다고 생각한다"고 말하며 "조금 더 좁아지고 있는 것으로 보인다"고 덧붙였습니다. 

            RBA의 성명은 "최근 예산 결과가 수요에 영향을 미칠 수 있지만, 연방 및 주 에너지 환급이 일시적으로 헤드 라인 인플레이션을 낮출 것"이라고 언급했습니다.

            BDO의 경제 파트너인 앤더스 마그누손은 스위스, 캐나다, 유럽 중앙은행이 금리를 인하했지만 "호주 경제는 아직 따라갈 준비가 되어 있지 않다"고 말했습니다.

            그는 "실업률은 역사적으로 낮은 수준을 유지하고 있지만, 일자리 공석과 주당 평균 근로 시간이 감소하면서 노동 시장은 분명히 완화되고 있습니다."라고 말했습니다. 

            "최근 가계 저축률 하락과 소매 지출 약세와 함께 RBA는 노동 시장이나 내수에 대해 걱정할 필요가 없다"고 덧붙였습니다. 

            마그누손은 다음 조치는 기준금리 인하가 될 것이지만 2025년 1분기가 되어야 할 것으로 예상합니다.
        """.trimIndent()
    )

    NewsDetailScreen(
        newsDetailUiState = NewsDetailUiState(),
        navigateToBack = { },
        scrollState = rememberLazyListState()
    )
}
