package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class Sleep(
    @SerializedName("date")
    val date: String,
    @SerializedName("sleepDuration")
    val sleepDuration: Double
)