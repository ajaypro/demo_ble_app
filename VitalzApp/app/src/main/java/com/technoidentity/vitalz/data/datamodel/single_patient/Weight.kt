package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("date")
    val date: String,
    @SerializedName("weight")
    val weight: Double
)