package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class NewsBean(
    @SerializedName("total") val itemCount: Int,
    @SerializedName("rows") val newsDataList: List<NewsData>
) {
    data class NewsData(
        @SerializedName("id") val id: Int,
        @SerializedName("type") val newsType: Int,
        @SerializedName("content") val newsContent: String,
        @SerializedName("cover") val imageUrl: String,
        @SerializedName("title") val newsTitle: String
    )
}
