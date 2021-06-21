package com.technoidentity.vitalz.bluetooth.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

sealed class Devices{

}

@Parcelize
data class RegisteredDevice(
    @SerializedName("patchId")
    val patchId: String,
    @SerializedName("macId")
    val macId: String
): Parcelable, Devices()
