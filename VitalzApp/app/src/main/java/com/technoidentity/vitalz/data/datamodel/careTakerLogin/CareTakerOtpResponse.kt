package com.technoidentity.vitalz.data.datamodel.careTakerLogin

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CareTakerOtpResponse (
    @Expose
    @SerializedName("reason")
    var reason: String? = null,
    @Expose
    @SerializedName("success")
    var success: Boolean? = null
): Parcelable