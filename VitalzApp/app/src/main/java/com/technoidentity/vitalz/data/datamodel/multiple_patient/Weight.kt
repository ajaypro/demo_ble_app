package com.technoidentity.vitalz.data.datamodel.multiple_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weight(
    @SerializedName("date")
    val date: String,
    @SerializedName("weight")
    val weight: Double
): Parcelable