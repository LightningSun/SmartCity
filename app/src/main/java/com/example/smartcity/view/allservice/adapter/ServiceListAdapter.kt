package com.example.smartcity.view.allservice.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ItemServiceBinding
import com.example.smartcity.logic.bean.ServiceBean
import com.example.smartcity.view.findhouse.FindHouseActivity

class ServiceListAdapter(
    private val context: Context,
    private var serviceDataList: List<ServiceBean.ServiceData> = emptyList(),
    private val maxSize: Int = 0
) :
    RecyclerView.Adapter<ServiceListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        serviceDataList =
            serviceDataList.sortedWith(compareByDescending { it.sort ?: Int.MIN_VALUE })
        Glide.with(binding.imgServiceIcon)
            .load(ApiConstants.BASE_URL + serviceDataList[position].imageUrl)
            .into(binding.imgServiceIcon)
        binding.txtServiceName.text = serviceDataList[position].serviceName

        binding.root.setOnClickListener {
            if (serviceDataList[position].serviceName == "找房子") {
                val intent = Intent(context, FindHouseActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int =
        if (maxSize != 0 && maxSize < serviceDataList.size) maxSize else serviceDataList.size


    fun setData(serviceDataList: List<ServiceBean.ServiceData>) {
        this.serviceDataList = serviceDataList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemServiceBinding) : RecyclerView.ViewHolder(binding.root)
}
