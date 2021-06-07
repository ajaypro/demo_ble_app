package com.technoidentity.vitalz.data.datamodel.hospital_list

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("zipCode")
    val zipCode: String?
):Parcelable