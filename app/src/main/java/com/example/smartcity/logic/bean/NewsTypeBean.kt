package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class NewsTypeBean(
    @SerializedName("msg") val message: String?,
    @SerializedName("code") val code: Int,
    @SerializedName("data") val newsTypeDataList: List<NewsTypeData>
) {
    data class NewsTypeData(
        @SerializedName("id") val newsTypeId: Int, @SerializedName("name") val newsType: String
    )
}
