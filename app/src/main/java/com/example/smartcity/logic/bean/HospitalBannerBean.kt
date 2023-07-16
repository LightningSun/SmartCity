package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class HospitalBannerBean(
    @SerializedName("msg") val response: String,
    @SerializedName("code") val responseCode: Int,
    @SerializedName("data") val bannerDataList: List<BannerData>
) {
    data class BannerData(
        @SerializedName("imgUrl") val imageUrl: String
    )
}