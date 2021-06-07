package com.technoidentity.vitalz.data.datamodel.otp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtpResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
):Parcelable