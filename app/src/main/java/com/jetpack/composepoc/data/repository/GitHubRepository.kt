package com.jetpack.composepoc.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jetpack.composepoc.data.Result
import com.jetpack.composepoc.data.model.GetUserRepositories
import com.jetpack.composepoc.data.model.GetGitHubUserList
import com.jetpack.composepoc.data.pagination.ReposPagingSource
import com.jetpack.composepoc.data.services.GitHubApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val apiService: GitHubApiService
) {
    suspend fun getUser(username: String): Result<GetGitHubUserList> {
        return try {
            val user = apiService.getUser(username)
            Result.Success(user)
        } catch (e: HttpException) {
            if (e.code() == 404) Result.Error.NotFoundError()
            else Result.Error.UnknownError()
        } catch (e: IOException) {
            Result.Error.NetworkError()
        } catch (e: Exception) {
            Result.Error.UnknownError()
        }
    }

    fun getReposPager(username: String): Flow<PagingData<GetUserRepositories>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { ReposPagingSource(apiService, username) }
        ).flow
    }
}
