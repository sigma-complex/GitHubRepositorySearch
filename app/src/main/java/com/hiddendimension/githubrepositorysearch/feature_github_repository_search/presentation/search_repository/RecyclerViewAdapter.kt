package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.presentation.search_repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hiddendimension.githubrepositorysearch.databinding.ReporitoryListRowBinding
import com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model.RepositoryData

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<RepositoryData>? = null

    fun setListData(listData: List<RepositoryData>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ReporitoryListRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder) {
            with(listData?.get(position)!!) {
                binding.tvName.text = this.name
                binding.tvDesc.text = this.description

                Glide.with(binding.imageAvatarUrl)
                    .load(this.owner?.avatar_url)
                    .into(binding.imageAvatarUrl)
            }
        }
    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        return listData?.size!!
    }


    inner class MyViewHolder(val binding: ReporitoryListRowBinding) :
        RecyclerView.ViewHolder(binding.root)

}