package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class MessageBean(
    @SerializedName("code") val responseCode: Int,
    @SerializedName("msg") val responseMessage: String
)