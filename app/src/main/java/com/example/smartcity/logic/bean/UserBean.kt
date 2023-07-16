package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class UserBean(
    @SerializedName("msg") val responseMsg: String,
    @SerializedName("code") val responseCode: Int,
    @SerializedName("user") val userData: UserData
) {
    data class UserData(
        @SerializedName("userId") val userId: Int,
        @SerializedName("userName") val userName: String,
        @SerializedName("nickName") val nickName: String,
        @SerializedName("email") val email: String?,
        @SerializedName("phonenumber") val phoneNumber: String,
        @SerializedName("sex") val sex: Int,
        @SerializedName("avatar") val avatarUrl: String,
        @SerializedName("idCard") val idCard: String,
        @SerializedName("balance") val balance: Double,
        @SerializedName("score") val score: Int
    )

}
