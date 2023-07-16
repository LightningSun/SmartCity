package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class ParkingBean(
    @SerializedName("total") val parkingCount: Int,
    @SerializedName("rows") val parkingDataList: List<ParkingData>
) {
    data class ParkingData(
        @SerializedName("searchValue") val searchValue: String,
        @SerializedName("parkName") val parkingName: String,
        @SerializedName("imgUrl") val imgUrl: String,
        @SerializedName("vacancy") val vacancy: Int,
        @SerializedName("address") val parkingAddress: String,
        @SerializedName("rates") val parkingPrice: Int
    )
}
