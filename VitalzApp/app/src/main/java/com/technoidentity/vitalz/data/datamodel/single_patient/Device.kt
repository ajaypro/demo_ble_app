package com.technoidentity.vitalz.data.datamodel.single_patient

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("betteryPercentage")
    val batteryPercentage: Double,
    @SerializedName("connection")
    val connection: String,
    @SerializedName("patchId")
    val patchId: String,
    @SerializedName("status")
    val status: String
)