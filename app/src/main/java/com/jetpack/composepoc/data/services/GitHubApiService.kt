package com.jetpack.composepoc.data.services
import com.jetpack.composepoc.data.model.GetUserRepositories
import com.jetpack.composepoc.data.model.GetGitHubUserList
import com.jetpack.composepoc.data.networking.GET_USER
import com.jetpack.composepoc.data.networking.GET_USER_REPOS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET(GET_USER)
    suspend fun getUser(@Path("username") username: String): GetGitHubUserList

    @GET(GET_USER_REPOS)
    suspend fun getRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<GetUserRepositories>
}
