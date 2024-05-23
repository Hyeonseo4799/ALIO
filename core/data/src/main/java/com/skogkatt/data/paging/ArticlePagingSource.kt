package com.skogkatt.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skogkatt.network.model.NetworkArticle
import com.skogkatt.network.model.NetworkArticleResponse

private const val StartingPage = 1

internal class ArticlePagingSource(
    private val getAllArticles: suspend (page: Int) -> NetworkArticleResponse
) : PagingSource<Int, NetworkArticle>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkArticle> {
        val currentPage = params.key ?: StartingPage
        val response = getAllArticles(currentPage)

        return LoadResult.Page(
            data = response.articles,
            prevKey = if (currentPage == StartingPage) null else currentPage - 1,
            nextKey = if (currentPage >= response.pages) null else currentPage + 1,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, NetworkArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { anchorPage ->
                anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
            }
        }
    }
}
