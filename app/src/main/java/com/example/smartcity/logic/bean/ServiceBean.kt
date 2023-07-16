package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class ServiceBean(
    @SerializedName("total") val itemCont: Int,
    @SerializedName("rows") val serviceDataList: List<ServiceData>
) {
    data class ServiceData(
        @SerializedName("id") val id: Int,
        @SerializedName("imgUrl") val imageUrl: String,
        @SerializedName("serviceType") val serviceType: String,
        @SerializedName("serviceName") val serviceName: String,
        @SerializedName("sort") val sort: Int?
    )
}
