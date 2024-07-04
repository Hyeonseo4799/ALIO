package com.skogkatt.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skogkatt.network.model.article.ArticleListResponse
import com.skogkatt.network.model.article.ArticleResponse

private const val StartingPage = 1

internal class ArticlePagingSource(
    private val getArticles: suspend (page: Int) -> ArticleListResponse
) : PagingSource<Int, ArticleResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleResponse> {
        val currentPage = params.key ?: StartingPage
        val response = getArticles(currentPage)

        return LoadResult.Page(
            data = response.articleResponses,
            prevKey = if (currentPage == StartingPage) null else currentPage - 1,
            nextKey = if (currentPage >= response.currentPage) null else currentPage + 1,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { anchorPage ->
                anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
            }
        }
    }
}
