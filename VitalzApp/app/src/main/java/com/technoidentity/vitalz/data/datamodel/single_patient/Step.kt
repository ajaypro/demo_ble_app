package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Step(
    @Expose
    @SerializedName("date")
    val date: String,
    @Expose
    @SerializedName("stepCount")
    val stepCount: Int
):Parcelable