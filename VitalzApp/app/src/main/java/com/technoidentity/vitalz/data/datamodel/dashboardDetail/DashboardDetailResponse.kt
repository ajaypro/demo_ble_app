package com.technoidentity.vitalz.data.datamodel.dashboardDetail

import com.google.gson.annotations.SerializedName

data class DashboardDetailResponse(
    @SerializedName("itemData")
    val itemData: List<ItemData>,
    @SerializedName("patientId")
    val patientId: String,
    @SerializedName("weeklyAverage")
    val weeklyAverage: Int,
    @SerializedName("weeklyMax")
    val weeklyMax: Int,
    @SerializedName("weeklyMin")
    val weeklyMin: Int
)