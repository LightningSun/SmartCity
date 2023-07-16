package com.example.smartcity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ActivityMainBinding
import com.example.smartcity.logic.bean.HouseBean
import com.example.smartcity.logic.bean.LoginBean
import com.example.smartcity.logic.bean.PostLoginBean
import com.example.smartcity.logic.global.GlobalUserDataHolder
import com.example.smartcity.logic.network.OkHttpUtils
import com.example.smartcity.view.allservice.AllServiceFragment
import com.example.smartcity.view.findhouse.FindHouseActivity
import com.example.smartcity.view.home.HomeFragment
import com.example.smartcity.view.home.adapter.ImageBannerAdapter
import com.example.smartcity.view.me.MeFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.youth.banner.indicator.CircleIndicator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentList = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentList.add(HomeFragment())
        fragmentList.add(AllServiceFragment())
        fragmentList.add(MeFragment())

        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragmentList.size
            override fun createFragment(position: Int): Fragment = fragmentList[position]
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "主页"
                1 -> tab.text = "全部服务"
                2 -> tab.text = "个人中心"
                else -> tab.text = "无标题"
            }
        }.attach()

        OkHttpUtils.postRequest(ApiConstants.LOGIN_API, Gson().toJson(PostLoginBean("stu01", "123"))) {
            GlobalUserDataHolder.loginToken = Gson().fromJson(it, LoginBean::class.java).token
        }
    }
}
