package com.technoidentity.vitalz.bluetooth.data

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.technoidentity.vitalz.bluetooth.BleConnection
import com.technoidentity.vitalz.utils.DEVICE_BATTERY_SER_UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*


data class BleDevice(
    val device: BluetoothDevice,
    val services: List<BluetoothGattService> = emptyList(),
    var connectionStatus: BleConnection = BleConnection.BtDisconnectedState
)

fun characteristicList(services: List<BluetoothGattService>): List<BluetoothGattCharacteristic> =
    services.listIterator().next().characteristics

fun batteryCharacteristic(services: List<BluetoothGattService>, characteristicUUID: UUID): LiveData<ByteArray> {
    lateinit var batteryValue: Flow<ByteArray>
    services.find { it.uuid == DEVICE_BATTERY_SER_UUID }
       ?.getCharacteristic(characteristicUUID)?.value?.let {
           batteryValue = flowOf(it)
        }
//            batteryValue = it.toString()
//        }
    return batteryValue.asLiveData()
}








