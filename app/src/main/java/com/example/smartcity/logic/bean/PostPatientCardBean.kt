package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class PostPatientCardBean(
    @SerializedName("address") val address: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("cardId") val cardId: String,
    @SerializedName("name") val name: String,
    @SerializedName("sex") val sex: Int,
    @SerializedName("tel") val tel: String
)
