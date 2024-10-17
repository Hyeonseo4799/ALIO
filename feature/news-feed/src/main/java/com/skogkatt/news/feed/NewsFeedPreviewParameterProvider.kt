package com.skogkatt.news.feed

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.skogkatt.model.article.Article
import kotlinx.coroutines.flow.flowOf

private val EditorPicks = List(5) {
    Article(
        id = "australia-news/article/2024/jun/18/reserve-bank-leaves-interest-rate-on-hold-at-435-with-borrowers-left-waiting-for-relief",
        sectionId = "australia-news",
        publishedAt = "10분 전",
        title = "중앙 은행은 대출자들이 구제를 기다리는 동안 금리를 4.35%로 동결합니다.",
        thumbnailUrl = "https://media.guim.co.uk/fe3089b924e907625af3b3d3d82a7efae9f20cb7/0_41_3235_1941/500.jpg"
    )
}

private val LatestArticles = List(10) {
    Article(
        id = "business/article/2024/jun/18/investment-in-uk-has-trailed-other-g7-countries-since-mid-1990s-ippr-says/$it",
        sectionId = "business",
        publishedAt = "1시간 전",
        title = "영국에 대한 투자는 1990 년대 중반 이후 다른 G7 국가를 뒤쫓고 있다고 IPPR은 말합니다.",
        thumbnailUrl = "https://media.guim.co.uk/ae194759ab246c9f9d80ac7ec6209888b31dd133/0_373_5559_3337/500.jpg"
    )
}

private val LatestArticlesPagingData = flowOf(PagingData.from(LatestArticles))

internal class NewsFeedPreviewParameterProvider : PreviewParameterProvider<NewsFeedUiState> {
    override val values: Sequence<NewsFeedUiState> = sequenceOf(
        NewsFeedUiState(
            editorsPicks = EditorPicks,
            latestArticles = LatestArticlesPagingData
        )
    )
}
