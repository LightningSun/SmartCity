package com.example.smartcity.view.findhouse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ItemHouseListBinding
import com.example.smartcity.logic.bean.HouseBean

class HouseListAdapter(private var houseDataList: List<HouseBean.HouseData> = emptyList()) :
    RecyclerView.Adapter<HouseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHouseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        Glide.with(binding.imageView5)
            .load(ApiConstants.BASE_URL + houseDataList[position].houseImgUrl)
            .into(binding.imageView5)
    }

    override fun getItemCount(): Int = houseDataList.size

    fun setData(houseDataList: List<HouseBean.HouseData>) {
        this.houseDataList = houseDataList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemHouseListBinding) : RecyclerView.ViewHolder(binding.root)
}