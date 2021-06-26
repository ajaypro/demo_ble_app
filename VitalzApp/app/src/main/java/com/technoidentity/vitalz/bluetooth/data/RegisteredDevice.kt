package com.technoidentity.vitalz.bluetooth.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

sealed class Devices{

}

@Parcelize
data class RegisteredDevice(
    @SerializedName("patchId")
    val patchId: String = "Invalid Patch",
    @SerializedName("macId")
    val macId: String= "Invalid MacId",
    var error: String
): Parcelable, Devices()
