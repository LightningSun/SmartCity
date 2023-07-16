package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class HospitalInfoBean(
    @SerializedName("total") val hospitalNum: Int,
    @SerializedName("rows") val hospitalDataList: List<HospitalData>
) {
    data class HospitalData(
        @SerializedName("id") val hospitalId: Int,
        @SerializedName("hospitalName") val hospitalName: String,
        @SerializedName("level") val level: Int,
        @SerializedName("imgUrl") val imgUrl: String,
        @SerializedName("brief") val hospitalBrief: String
    )
}
