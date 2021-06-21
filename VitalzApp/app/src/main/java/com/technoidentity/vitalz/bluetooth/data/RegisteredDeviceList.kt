package com.technoidentity.vitalz.bluetooth.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisteredDeviceList(

    val registeredDevice: RegisteredDevice
):Parcelable
