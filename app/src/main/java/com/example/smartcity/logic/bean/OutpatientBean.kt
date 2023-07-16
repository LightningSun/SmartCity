package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class OutpatientBean(
    @SerializedName("code") val response: Int,
    @SerializedName("rows") val OutpatientDataList: List<OutpatientData>
) {
    data class OutpatientData(
        @SerializedName("id") val id: Int,
        @SerializedName("type") val outpatientType: String,
        @SerializedName("categoryName") val categoryName: String,
        @SerializedName("money") val money: Int
    )
}
