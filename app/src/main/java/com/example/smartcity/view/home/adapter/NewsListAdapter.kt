package com.example.smartcity.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ItemNewsListBinding
import com.example.smartcity.logic.bean.NewsBean

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private var newsDataList: List<NewsBean.NewsData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        Glide.with(binding.newsImage)
            .load(ApiConstants.BASE_URL + newsDataList[position].imageUrl)
            .into(binding.newsImage)
        binding.newsTitle.text = newsDataList[position].newsTitle

        binding.root.setOnClickListener { }
    }

    override fun getItemCount(): Int = newsDataList.size

    fun setData(newsDataList: List<NewsBean.NewsData>) {
        this.newsDataList = newsDataList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root)
}
