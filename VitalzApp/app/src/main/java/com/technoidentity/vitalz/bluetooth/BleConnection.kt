package com.technoidentity.vitalz.bluetooth

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class BleConnection {

    object BtDisconnectedState : BleConnection()
    data class BtConnectingLoadingState(val device: BluetoothDevice) : BleConnection()
    class BtConnectedState : BleConnection()
    object BtErrorConnectingState : BleConnection()
}
