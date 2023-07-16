package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class LoginBean(
    @SerializedName("code") val responseCode: String,
    @SerializedName("msg") val responseMessage: String,
    @SerializedName("token") val token: String
)
