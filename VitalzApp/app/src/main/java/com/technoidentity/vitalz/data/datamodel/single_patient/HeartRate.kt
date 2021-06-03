package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeartRate(
    @SerializedName("date")
    val date: String,
    @SerializedName("ratePerMinute")
    val ratePerMinute: List<Int>
):Parcelable