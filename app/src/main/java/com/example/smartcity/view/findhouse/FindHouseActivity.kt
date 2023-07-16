package com.example.smartcity.view.findhouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ActivityFindHouseBinding
import com.example.smartcity.logic.bean.HouseBean
import com.example.smartcity.logic.network.OkHttpUtils
import com.example.smartcity.view.findhouse.adapter.HouseListAdapter
import com.example.smartcity.view.home.adapter.ImageBannerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.youth.banner.indicator.CircleIndicator

class FindHouseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindHouseBinding

    // 避免因网络问题造成的不同步
    private var currentSelection = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindHouseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 加载轮播图
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_HOUSE_LIST_API) { json ->
            val houseImgUrlList = mutableListOf<String>()
            Gson().fromJson(json, HouseBean::class.java).houseDataList.forEach { houseData ->
                houseImgUrlList.add(ApiConstants.BASE_URL + houseData.houseImgUrl)
            }

            runOnUiThread {
                binding.imageBanner.setAdapter(
                    ImageBannerAdapter(houseImgUrlList)
                ).addBannerLifecycleObserver(this).indicator = CircleIndicator(this)
            }
        }

        val houseListAdapter = HouseListAdapter()
        binding.houseList.layoutManager =
            object : GridLayoutManager(this@FindHouseActivity, 1) {
                override fun canScrollHorizontally(): Boolean = false
            }
        binding.houseList.adapter = houseListAdapter

        // 初始化房子列表
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_HOUSE_LIST_API + "?houseType=二手") { json ->
            if (currentSelection != binding.tabHouseType.selectedTabPosition) {
                return@getJsonByUrl
            }
            val houseDataList = Gson().fromJson(json, HouseBean::class.java).houseDataList
            runOnUiThread {
                houseListAdapter.setData(houseDataList)
            }
        }
        // 根据选择切换房子列表
        binding.tabHouseType.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentSelection = tab?.position ?: 0
                val houseType = tab?.text.toString()
                OkHttpUtils.getJsonByUrl(ApiConstants.GET_HOUSE_LIST_API + "?houseType=$houseType") { json ->
                    if (currentSelection != tab?.position) {
                        return@getJsonByUrl
                    }
                    val houseDataList = Gson().fromJson(json, HouseBean::class.java).houseDataList
                    runOnUiThread {
                        houseListAdapter.setData(houseDataList)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
