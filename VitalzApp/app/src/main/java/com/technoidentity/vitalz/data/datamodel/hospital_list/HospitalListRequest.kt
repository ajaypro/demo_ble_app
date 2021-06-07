package com.technoidentity.vitalz.data.datamodel.hospital_list

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HospitalListRequest(
    @SerializedName("phoneNo")
    var mobile: String? = null
) : Parcelable