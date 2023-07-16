package com.example.smartcity.logic.bean

import com.google.gson.annotations.SerializedName

data class PatientCardInfoBean(
    @SerializedName("total") val patientNum: Int,
    @SerializedName("code") val responseCode: Int,
    @SerializedName("rows") val PatientCardDataList: List<PatientCardData>
) {
    data class PatientCardData(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val patientName: String,
        @SerializedName("cardId") val cardId: String,
        @SerializedName("tel") val phoneNumber: String,
        @SerializedName("sex") val patientSex: Int,
        @SerializedName("birthday") val patientBirthday: String,
        @SerializedName("address") val address: String
    )
}
