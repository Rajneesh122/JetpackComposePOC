package com.jetpack.composepoc.data.pagination
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jetpack.composepoc.data.services.GitHubApiService
import com.jetpack.composepoc.data.model.GetUserRepositories
import retrofit2.HttpException
import java.io.IOException

class ReposPagingSource(
    private val apiService: GitHubApiService,
    private val username: String
) : PagingSource<Int, GetUserRepositories>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetUserRepositories> {
        val page = params.key ?: 1
        return try {
            val repos = apiService.getRepos(username, page, params.loadSize)
            LoadResult.Page(
                data = repos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetUserRepositories>): Int? =
        state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
