package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.data.repository

import androidx.lifecycle.LiveData
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.data.data_source.AppDao
import com.demo.paging3withroom.network.RetroServiceInterface
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.RepositoriesList
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.RepositoryData


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface,
                                          private val appDao: AppDao
) {

    fun getAllRecords(): LiveData<List<RepositoryData>> {
        return appDao.getAllRecords()
    }

    fun insertRecord(repositoryData: RepositoryData) {
        appDao.insertRecords(repositoryData)
    }

    //get the data from github api...
    fun makeApiCall(query: String?) {
        val call: Call<RepositoriesList> = retroServiceInterface.getDataFromAPI(query!!)
        call.enqueue(object : Callback<RepositoriesList>{
            override fun onResponse(
                call: Call<RepositoriesList>,
                response: Response<RepositoriesList>
            ) {
                if(response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.items?.forEach {
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<RepositoriesList>, t: Throwable) {
                //
            }
        })
    }
}