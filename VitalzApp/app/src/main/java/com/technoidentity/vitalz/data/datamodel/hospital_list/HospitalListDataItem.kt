package com.technoidentity.vitalz.data.datamodel.hospital_list

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HospitalListDataItem(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("hospitalName")
    val hospitalName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("status")
    val status: Boolean?
):Parcelable