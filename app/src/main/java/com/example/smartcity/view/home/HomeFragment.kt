package com.example.smartcity.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.FragmentHomeBinding
import com.example.smartcity.logic.bean.HomeBannerBean
import com.example.smartcity.logic.bean.NewsBean
import com.example.smartcity.logic.bean.NewsTypeBean
import com.example.smartcity.logic.bean.ServiceBean
import com.example.smartcity.logic.network.OkHttpUtils
import com.example.smartcity.view.allservice.adapter.ServiceListAdapter
import com.example.smartcity.view.home.adapter.ImageBannerAdapter
import com.example.smartcity.view.home.adapter.NewsListAdapter
import com.example.smartcity.view.home.adapter.NewsSpecialAdapter
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val bannerUrlList: MutableList<String> = mutableListOf()
    private var newsDataList = emptyList<NewsBean.NewsData>()
    private var newsTypeDataList = emptyList<NewsTypeBean.NewsTypeData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        // 加载轮播图
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_HOME_BANNER_API) { json ->
            Gson().fromJson(json, HomeBannerBean::class.java).imageDataList.forEach { bannerData ->
                bannerUrlList.add(ApiConstants.BASE_URL + bannerData.imageUrl)
            }
            activity?.runOnUiThread {
                binding.imageBanner.setAdapter(ImageBannerAdapter(bannerUrlList))
                    .addBannerLifecycleObserver(this)
                    .indicator = CircleIndicator(context)
            }
        }

        // 加载服务列表
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_ALL_SERVICE_API) { json ->
            val serviceDataList = Gson().fromJson(json, ServiceBean::class.java).serviceDataList
            activity?.runOnUiThread {
                binding.allServiceList.layoutManager = object : GridLayoutManager(context, 5) {
                    override fun canScrollHorizontally(): Boolean = false
                    override fun canScrollVertically(): Boolean = false
                }
                binding.allServiceList.adapter =
                    ServiceListAdapter(this.requireContext(), serviceDataList, 10)
            }
        }

        // 加载推荐新闻
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_NEWS_API + "?hot=Y") { json ->
            val newsDataList = Gson().fromJson(json, NewsBean::class.java).newsDataList
            activity?.runOnUiThread {
                binding.newSpecialList.layoutManager = object : GridLayoutManager(context, 2) {
                    override fun canScrollHorizontally(): Boolean = false
                    override fun canScrollVertically(): Boolean = false
                }
                binding.newSpecialList.adapter = NewsSpecialAdapter(newsDataList, 2)
            }
        }

        val newsListAdapter = NewsListAdapter()
        binding.newsList.adapter = newsListAdapter

        // 设置新闻分类标题
        OkHttpUtils.getJsonByUrl(ApiConstants.GET_NEWS_TYPE_API) { json ->
            newsTypeDataList = Gson().fromJson(json, NewsTypeBean::class.java).newsTypeDataList
            activity?.runOnUiThread {
                newsTypeDataList.forEach { itemNewsType ->
                    val newsTypeTab = binding.newsTabLayout.newTab()
                    newsTypeTab.text = itemNewsType.newsType
                    binding.newsTabLayout.addTab(newsTypeTab)
                }
            }

            // 获取全部新闻
            OkHttpUtils.getJsonByUrl(ApiConstants.GET_NEWS_API) { newsDataJson ->
                newsDataList = Gson().fromJson(newsDataJson, NewsBean::class.java).newsDataList
                var showNewsList = newsDataList.filter {
                    it.newsType == newsTypeDataList[0].newsTypeId
                }

                activity?.runOnUiThread {
                    newsListAdapter.setData(showNewsList)
                    binding.newsList.layoutManager = object : GridLayoutManager(context, 1) {
                        override fun canScrollVertically(): Boolean = false
                        override fun canScrollHorizontally(): Boolean = false
                    }
                    binding.newsTabLayout.addOnTabSelectedListener(object :
                        TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            showNewsList = newsDataList.filter {
                                it.newsType == newsTypeDataList[tab?.position ?: 0].newsTypeId
                            }
                            activity?.runOnUiThread {
                                newsListAdapter.setData(showNewsList)
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                }
            }

        }

        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
