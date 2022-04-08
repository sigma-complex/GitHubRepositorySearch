package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.data.repository

import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.RepositoriesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("repositories")
    fun getDataFromAPI(@Query("q")query: String): Call<RepositoriesList>
}