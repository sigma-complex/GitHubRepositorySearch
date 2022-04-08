package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository

object SearchUtil {

    fun validateSearch(searchQuery: String):Boolean{
        return searchQuery.isNotEmpty()
    }
}