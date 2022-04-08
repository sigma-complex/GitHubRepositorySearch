package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository.repositories

import androidx.lifecycle.MutableLiveData
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.Owner
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.RepositoryData
import com.hiddendimension.githubrepositorysearch.other.Resource

class FakeGitHubRepository {

    private val gitHubRepositories = mutableListOf<RepositoryData>()
    private val observableGitHubRepositories =
        MutableLiveData<List<RepositoryData>>(gitHubRepositories)

    private var shouldReturnNetworkError = false;

    fun shouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }


    fun insertSomeDummyData(searchQuery: String) {

        for (i in 1..10) {
            gitHubRepositories.add(
                RepositoryData(
                    i,
                    searchQuery + " Name " + i.toChar(),
                    "description of " + i.toChar(),
                    Owner("http://owner.com/" + i)
                )
            )
        }

        //  refreshLiveData()
    }

    fun refreshLiveData() {
        observableGitHubRepositories.postValue(gitHubRepositories)
    }

    fun getDataFromAPI(query: String): Resource<MutableList<RepositoryData>> {

        if (shouldReturnNetworkError) {
            return Resource.error("error", null)
        } else {
            insertSomeDummyData(query)
            return Resource.success(gitHubRepositories)
        }
    }


}