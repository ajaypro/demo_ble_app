package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class BloodPressure(
    @SerializedName("date")
    val date: String,
    @SerializedName("diastolicPressure")
    val diastolicPressure: List<Int>,
    @SerializedName("systolicPressure")
    val systolicPressure: List<Int>
)