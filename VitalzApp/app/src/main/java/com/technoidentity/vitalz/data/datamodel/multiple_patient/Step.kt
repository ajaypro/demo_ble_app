package com.technoidentity.vitalz.data.datamodel.multiple_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Step(
    @SerializedName("date")
    val date: String,
    @SerializedName("stepCount")
    val stepCount: Int
):Parcelable