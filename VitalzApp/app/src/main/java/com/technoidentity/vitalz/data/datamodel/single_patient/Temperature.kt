package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Temperature(
    @SerializedName("bodyTemprature")
    val bodyTemperature: List<Double>,
    @SerializedName("date")
    val date: String
): Parcelable