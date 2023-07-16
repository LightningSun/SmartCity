package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class PostChangePasswordBean(
    @SerializedName("newPassword") val newPassword: String,
    @SerializedName("oldPassword") val oldPassword: String
)