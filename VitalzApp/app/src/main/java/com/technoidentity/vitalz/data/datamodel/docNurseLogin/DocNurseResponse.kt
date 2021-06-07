package com.technoidentity.vitalz.data.datamodel.docNurseLogin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DocNurseResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
):Parcelable