package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class HeartRate(
    @SerializedName("date")
    val date: String,
    @SerializedName("ratePerMinute")
    val ratePerMinute: List<Int>
)