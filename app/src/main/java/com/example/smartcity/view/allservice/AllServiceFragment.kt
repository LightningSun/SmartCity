package com.example.smartcity.view.allservice

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.FragmentAllServiceBinding
import com.example.smartcity.logic.bean.ServiceBean
import com.example.smartcity.logic.network.OkHttpUtils
import com.example.smartcity.view.allservice.adapter.ServiceListAdapter
import com.google.gson.Gson

class AllServiceFragment : Fragment() {
    private lateinit var binding: FragmentAllServiceBinding
    private val selectButtonList: MutableList<TextView> = mutableListOf()
    private val serviceDataList = MutableList<List<ServiceBean.ServiceData>>(3) { mutableListOf() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllServiceBinding.inflate(layoutInflater)

        binding.run {
            selectButtonList.add(btnSelect1)
            selectButtonList.add(btnSelect2)
            selectButtonList.add(btnSelect3)
        }
        val serviceListAdapter = ServiceListAdapter(this.requireContext())
        binding.setviceList.adapter = serviceListAdapter
        binding.setviceList.layoutManager = object : GridLayoutManager(context, 3) {
            override fun canScrollHorizontally(): Boolean = false
        }
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_ALL_SERVICE_API + "?serviceType=便民服务") { json ->
            serviceDataList[0] = (Gson().fromJson(json, ServiceBean::class.java).serviceDataList)
            activity?.runOnUiThread {
                selectButtonList[0].setBackgroundColor(Color.parseColor("#AFAFAF"))
                serviceListAdapter.setData(serviceDataList[0])
            }
        }
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_ALL_SERVICE_API + "?serviceType=生活服务") { json ->
            serviceDataList[1] = (Gson().fromJson(json, ServiceBean::class.java).serviceDataList)
        }
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_ALL_SERVICE_API + "?serviceType=车主服务") { json ->
            serviceDataList[2] = (Gson().fromJson(json, ServiceBean::class.java).serviceDataList)
        }
        selectButtonList.forEachIndexed { index, btnSelect ->
            btnSelect.setOnClickListener { view ->
                if (serviceDataList[index].isEmpty()) {
                    Toast.makeText(context, "该服务正在加载", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                serviceListAdapter.setData(serviceDataList[index])
                selectButtonList.forEach {
                    it.setBackgroundColor(Color.TRANSPARENT)
                }
                view.setBackgroundColor(Color.parseColor("#AFAFAF"))
            }
        }
        return binding.root
    }
}
