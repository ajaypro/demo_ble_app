package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("date")
    val date: String,
    @SerializedName("stepCount")
    val stepCount: Int
)