package com.technoidentity.vitalz.data.datamodel.docNurseLogin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneExt")
    val phoneExt: String?,
    @SerializedName("phoneNo")
    val phoneNo: String?,
    @SerializedName("role")
    val role: String?
):Parcelable