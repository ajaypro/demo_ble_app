package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class Posture(
    @SerializedName("bodyPosture")
    val bodyPosture: List<String>,
    @SerializedName("date")
    val date: String
)