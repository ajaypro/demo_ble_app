package com.technoidentity.vitalz.data.datamodel.multiple_patient

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeartRate(
    val date: String,
    val ratePerMinute: List<Int>
): Parcelable