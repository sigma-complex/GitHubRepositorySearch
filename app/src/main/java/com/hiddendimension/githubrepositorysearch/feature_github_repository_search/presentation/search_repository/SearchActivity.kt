package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiddendimension.githubrepositorysearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureActivity()
        initRecylerView()
        observeLiveData()


    }


    fun configureActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.mainActivityViewModel = mainActivityViewModel
        binding.lifecycleOwner = this
    }


    private fun initRecylerView() {
        binding.repositoryListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)

            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveData() {

            binding.mainActivityViewModel?.getAllRepositoryList()?.observe(this, {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()

        })


      //  mainActivityViewModel.makeApiCall()
    }


}