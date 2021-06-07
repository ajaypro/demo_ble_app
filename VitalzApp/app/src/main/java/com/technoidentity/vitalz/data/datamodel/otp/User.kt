package com.technoidentity.vitalz.data.datamodel.otp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneNo")
    val phoneNo: String?,
):Parcelable