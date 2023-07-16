package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class PostLoginBean(
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String
)