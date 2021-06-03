package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Posture(
    @SerializedName("bodyPosture")
    val bodyPosture: List<String>,
    @SerializedName("date")
    val date: String
): Parcelable