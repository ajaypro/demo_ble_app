package com.technoidentity.vitalz.utils

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.widget.Toast
import com.technoidentity.vitalz.bluetooth.BleConnection
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.*


val HEART_RATE_SER_UUID = UUID.fromString("03B80E5A-EDE8-4B33-A751-6CE34EC4C700")
val HEART_RATE_CHAR_UUID = UUID.fromString("03B80E5B-EDE8-4B33-A751-6CE34EC4C700")
val DEVICE_BATTERY_SER_UUID = UUID.fromString("04b8055c-ede8-4b44-a785-6ce34ec4c755")
val DEVICE_BATTERY_CHAR_UUID = UUID.fromString("04b87594-ede8-4b44-a785-6ce34ec4c755")
const val CHAR_READ_INITIALIZED_SUCCESS = "Character read initialized successfully"
const val CHAR_READ_INITIALIZED_FAILURE = "Character read failed"
const val DEVICE_START_NAME = "HRM"


var HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb"
var CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb"

// object Attributes{
//
//     var attributes = mutableMapOf<String, String>()
//
//
//    // Sample Services.
//
//    attributes["0000180d-0000-1000-8000-00805f9b34fb"] = "Heart Rate Service"
//    attributes["0000180a-0000-1000-8000-00805f9b34fb"] = "Device Information Service"
//    // Sample Characteristics.
//    attributes[HEART_RATE_MEASUREMENT] = "Heart Rate Measurement"
//    attributes["00002a29-0000-1000-8000-00805f9b34fb"] = "Manufacturer Name String"
//}
//
//fun lookup(uuid: String, defaultName: String?): String? {
//    val name = attributes[uuid]
//    return name ?: defaultName
//}

fun BluetoothDevice.patchId(patchId: String? = null): String = patchId ?: "Unnamed"

fun equalsDevice(macId: String, bluetoothDevice: BluetoothDevice): Boolean = (macId == bluetoothDevice.address)


fun BluetoothDevice.diplayName(device: BluetoothDevice) = if (device.name.startsWith(DEVICE_START_NAME)) device.name else "Unnamed"

suspend fun connectAsClientAsync(bluetoothDevice: BluetoothDevice, channel: Int)
: Deferred<BluetoothSocket> {
    return withContext(Dispatchers.IO) {
        async {
            val bluetoothSocket = bluetoothDevice.createRfcommSocket(channel)
            bluetoothSocket.apply {
                connect()
            }
        }
    }
}

fun BluetoothDevice.createRfcommSocket(channel: Int): BluetoothSocket {
    try {
        val method = this.javaClass.getMethod("createRfcommSocket", Integer.TYPE)
        return method.invoke(this, channel) as BluetoothSocket
    } catch (e: Exception) {
        throw UnsupportedOperationException(e)
    }
}

fun checkBtConnectionState(btConnection: BleConnection, context: Context) {
        when (btConnection) {
            is BleConnection.BtConnectingLoadingState -> {
                //context.showSnackbar(R.string.connection_loading,Snackbar.LENGTH_SHORT)
                Toast.makeText(context, "CONNECTING PLEASE WAIT...", Toast.LENGTH_LONG).show()
            }
            is BleConnection.BtConnectedState -> {
                //context.showSnackbar(R.string.connected,Snackbar.LENGTH_SHORT)
                Toast.makeText(context, "CONNECTED", Toast.LENGTH_LONG).show()
            }
            is BleConnection.BtErrorConnectingState -> {
                Toast.makeText(context, "SOMETHING WENT WRONG WHILE CONNECTING", Toast.LENGTH_LONG)
                    .show()
            }
            is BleConnection.BtDisconnectedState -> {
                Toast.makeText(context, "DISCONNECTED", Toast.LENGTH_SHORT).show()
            }
        }
    }
