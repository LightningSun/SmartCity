package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class HomeBannerBean(
    @SerializedName("total") val dataNumber: Int?,
    @SerializedName("rows") val imageDataList: List<BannerData>
) {
    data class BannerData(
        @SerializedName("searchValue") val searchValue: String?,
        @SerializedName("id") val id: Long?,
        @SerializedName("advImg") val imageUrl: String
    )
}
