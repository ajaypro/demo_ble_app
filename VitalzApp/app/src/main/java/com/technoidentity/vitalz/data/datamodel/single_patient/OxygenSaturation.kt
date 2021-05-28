package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class OxygenSaturation(
    @SerializedName("date")
    val date: String,
    @SerializedName("oxygenPercentage")
    val oxygenPercentage: List<Double>
)