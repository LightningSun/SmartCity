package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class PostUserDataBean(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("phonenumber") val phoneNumber: String,
    @SerializedName("sex") val sex: String,
//    @SerializedName("avatar") val avatarUrl: String,
    @SerializedName("idCard") val idCard: String,
)
