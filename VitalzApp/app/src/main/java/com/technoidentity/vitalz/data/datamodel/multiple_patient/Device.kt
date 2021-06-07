package com.technoidentity.vitalz.data.datamodel.multiple_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Device(
    @SerializedName("betteryPercentage")
    val betteryPercentage: Double,
    @SerializedName("connection")
    val connection: String,
    @SerializedName("patchId")
    val patchId: String,
    @SerializedName("status")
    val status: String
): Parcelable