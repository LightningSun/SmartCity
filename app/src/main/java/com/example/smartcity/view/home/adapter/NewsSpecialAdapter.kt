package com.example.smartcity.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ItemNewsSpecialBinding
import com.example.smartcity.logic.bean.NewsBean

class NewsSpecialAdapter(
    private val serviceDataList: List<NewsBean.NewsData> = emptyList(),
    private val maxSize: Int = 0
) :
    RecyclerView.Adapter<NewsSpecialAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNewsSpecialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        Glide.with(binding.newsImage)
            .load(ApiConstants.BASE_URL + serviceDataList[position].imageUrl)
            .into(binding.newsImage)
        binding.newsTitle.text = serviceDataList[position].newsTitle

        binding.root.setOnClickListener {}
    }

    override fun getItemCount(): Int =
        if (maxSize != 0 && maxSize < serviceDataList.size) maxSize else serviceDataList.size


    class ViewHolder(val binding: ItemNewsSpecialBinding) : RecyclerView.ViewHolder(binding.root)
}