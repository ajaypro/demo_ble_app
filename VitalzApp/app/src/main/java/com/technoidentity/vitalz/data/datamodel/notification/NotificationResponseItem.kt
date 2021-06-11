package com.technoidentity.vitalz.data.datamodel.notification

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationResponseItem(
    @Expose
    @SerializedName("date")
    val date: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("user")
    val user: User
):Parcelable