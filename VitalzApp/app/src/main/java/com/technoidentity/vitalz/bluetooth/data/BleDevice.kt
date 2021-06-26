package com.technoidentity.vitalz.bluetooth.data

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattService
import com.technoidentity.vitalz.bluetooth.connection.BleConnection


data class BleDevice(
    val device: BluetoothDevice,
    val services: List<BluetoothGattService>? = emptyList(),
    var battery: String? = null,
    var connectionStatus: BleConnection = BleConnection.DeviceDisconnected
)








