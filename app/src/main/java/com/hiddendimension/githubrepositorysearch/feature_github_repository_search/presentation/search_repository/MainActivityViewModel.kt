package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.data.repository.RetroRepository
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.RepositoryData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository)
    : ViewModel(){

    var searchQuery: String?=""

    fun getAllRepositoryList(): LiveData<List<RepositoryData>> {
        return repository.getAllRecords()
    }

    fun makeApiCall() {
        repository.makeApiCall("ny")
    }

    fun searchOnClick(view : View){

        repository.makeApiCall(searchQuery)
    }
}