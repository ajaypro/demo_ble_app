package com.technoidentity.vitalz.bluetooth


import android.bluetooth.*
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.technoidentity.vitalz.bluetooth.data.BleDevice
import com.technoidentity.vitalz.utils.*
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.util.*

@ViewModelScoped
class BleManager(private val bleScanner: BleScanner) : IBleManager {

     lateinit  var bluetoothGatt: BluetoothGatt

    private var _isScanning = MutableStateFlow(false)
    override var isScanning: StateFlow<Boolean> = _isScanning

    private var _connectedBleDeviceLiveData = MutableLiveData<BleDevice>()
    override var connectedBleDeviceLiveData:LiveData<BleDevice> = _connectedBleDeviceLiveData

    private var _isDeviceConnected = MutableStateFlow(false)
    override var isDeviceConnected: StateFlow<Boolean> = _isDeviceConnected

    private var _heartRateCharacteristic = MutableStateFlow(byteArrayOf(0))
    override var heartRateCharacteristic: StateFlow<ByteArray> = _heartRateCharacteristic


    lateinit var connectedDevice: BleDevice

    lateinit var heartRateService: UUID

    //make use of this in singlepaitentdashboard fragment
    var gattOperationStatus = mutableMapOf<String, Boolean>()

    override fun startScan() {
        _isScanning.value = true
        bleScanner.startScan()
    }

    override fun stopScan() {
        _isScanning.value = false
        bleScanner.stopScan()
    }

    override var scanChannel: Channel<BluetoothDevice> = bleScanner.channel

//    override fun bleConnect(bleDevice: BluetoothDevice)  = flow {
//        emit(BleConnection.BtConnectingLoadingState(bleDevice))
//        try {
//            val socket = connectAsClientAsync(bleDevice, 2).await()
//            emit(BleConnection.BtConnectedState(socket = socket))
//        } catch (e: Exception) {
//            emit(BleConnection.BtErrorConnectingState)
//        }
//    }

    override fun connectDevice(device: BluetoothDevice, context: Context) {
        connectedDevice = BleDevice(device)
        bluetoothGatt = device.connectGatt(context, false, gattClientCallback)
    }

    override fun readCharacteristic(characteristic: BluetoothGattCharacteristic) {
        when {
            bluetoothGatt.readCharacteristic(characteristic) -> {
                gattOperationStatus[CHAR_READ_INITIALIZED_SUCCESS] = true
            }
            else -> {
                gattOperationStatus[CHAR_READ_INITIALIZED_FAILURE] = false
            }
        }
    }

    private val gattClientCallback = object : BluetoothGattCallback() {

        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (status != BluetoothGatt.GATT_SUCCESS) {
                disconnectGattServer(gatt, BleConnection.BtErrorConnectingState)
                return
            }

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                _isDeviceConnected.value = true.also {
                    Timber.d("BleManager device connected")
                }
                connectedDevice?.let {
                    _connectedBleDeviceLiveData.postValue(
                        BleDevice(
                            it.device,
                            it.services,
                            connectionStatus = BleConnection.BtConnectedState()
                        )
                    )
                }
                gatt?.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                disconnectGattServer(gatt,BleConnection.BtErrorConnectingState)
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            if (status != BluetoothGatt.GATT_SUCCESS) {
                return
            }
            gatt?.services?.let { services ->
                services.forEach {
                    if (it.uuid == HEART_RATE_SER_UUID) {
                        heartRateService = it.uuid
                    }
                }
            }
            gatt?.services?.let { services ->
                services.forEach { Log.i("services", "uuid: ${it.uuid}") }
                connectedDevice?.let {
                    connectedDevice =
                        BleDevice(it.device, services, BleConnection.BtConnectedState())
                    _connectedBleDeviceLiveData.postValue(connectedDevice)
                }
            }
        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?) {
            super.onCharacteristicChanged(gatt, characteristic)
            val messageString: String = try {
                characteristic?.value?.convertToString() ?: ""
            } catch (e: UnsupportedEncodingException) {
                Log.e(TAG, "Unable to convert message bytes to string")
                "Error"
            }
            Log.i(TAG, "characteristic value: $messageString")
            setValueToCharacteristic(characteristic)
        }

        override fun onCharacteristicWrite(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            Log.i(TAG, "onCharacteristicWrite status: $status")
        }

        override fun onCharacteristicRead(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
            super.onCharacteristicRead(gatt, characteristic, status)
            Log.i(TAG, "onCharacteristicRead status: $status, value: ${String(characteristic?.value ?: byteArrayOf(0))}")
            setValueToCharacteristic(characteristic)
            characteristic?.let {
                _heartRateCharacteristic.value = it.value
            }
            }
        }

        private fun setValueToCharacteristic(characteristic: BluetoothGattCharacteristic?) {
            connectedDevice?.let {
                if (it.services.isEmpty()) {
                    Log.i(TAG, "No services with characteristics found.")
                    return
                }
                it.services.first { it.characteristics.contains(characteristic) }
                    .getCharacteristic(characteristic?.uuid)?.value = characteristic?.value
                _connectedBleDeviceLiveData.postValue(
                    BleDevice(
                        it.device,
                        it.services,
                        BleConnection.BtConnectedState())
                )
            }

        }

    fun disconnectGattServer(gatt: BluetoothGatt?,status: BleConnection) {
        connectedDevice?.let {
            _connectedBleDeviceLiveData.postValue(BleDevice(it.device, it.services, status))
        }
        _isDeviceConnected.value = false.also {
                Timber.d("BleManager device connected")
        }
        gatt?.disconnect()
        gatt?.close()
    }
}