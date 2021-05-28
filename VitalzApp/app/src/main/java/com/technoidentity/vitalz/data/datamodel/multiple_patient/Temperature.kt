package com.technoidentity.vitalz.data.datamodel.multiple_patient

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("bodyTemprature")
    val bodyTemperature: List<Double>,
    @SerializedName("date")
    val date: String
)