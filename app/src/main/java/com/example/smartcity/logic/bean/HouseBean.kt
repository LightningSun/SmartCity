package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class HouseBean(
    @SerializedName("total") val total: Int,
    @SerializedName("msg") val responseMsg: String,
    @SerializedName("rows") val houseDataList: List<HouseData>
) {
    data class HouseData(
        @SerializedName("id") val id: Int,
        @SerializedName("pic") val houseImgUrl: String
    )
}