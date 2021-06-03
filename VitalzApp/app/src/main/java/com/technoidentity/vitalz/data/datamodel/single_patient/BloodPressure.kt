package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BloodPressure(
    @SerializedName("date")
    val date: String,
    @SerializedName("diastolicPressure")
    val diastolicPressure: List<Int>,
    @SerializedName("systolicPressure")
    val systolicPressure: List<Int>
): Parcelable