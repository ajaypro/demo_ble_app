package com.technoidentity.vitalz.data.datamodel.otp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class OtpRequest(
    @SerializedName("phoneNo")
    var phoneNo: String? = null,
    @SerializedName("otp")
    var otp: Int? = null
) : Parcelable