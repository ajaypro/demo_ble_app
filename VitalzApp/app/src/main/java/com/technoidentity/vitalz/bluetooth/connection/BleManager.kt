package com.technoidentity.vitalz.bluetooth.connection


import android.bluetooth.*
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.technoidentity.vitalz.bluetooth.data.BleDevice
import com.technoidentity.vitalz.utils.*
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.util.*

const val TAG = "BleManager"

@ViewModelScoped
class BleManager(private val bleScanner: BleScanner) : IBleManager {

    /**
     * Bluetooth device data
     */
    lateinit var bluetoothGatt: BluetoothGatt

    private var _isScanning = MutableStateFlow(false)
    override var isScanning: StateFlow<Boolean> = _isScanning

    private var _connectedBleDeviceLiveData = MutableLiveData<BleDevice>()
    override var connectedBleDeviceLiveData: LiveData<BleDevice> = _connectedBleDeviceLiveData

    private var _isDeviceConnected = MutableStateFlow(false)
    override var isDeviceConnected: StateFlow<Boolean> = _isDeviceConnected

    lateinit var connectedDevice: BleDevice

    lateinit var bluetoothGattService: BluetoothGattService

    /**
     * Patient data to be updated
     */
    private var _heartRateCharacteristic = MutableStateFlow(byteArrayOf(0))
    override var heartRateCharacteristic: StateFlow<ByteArray> = _heartRateCharacteristic

    private var _ecgCharacteristic = MutableStateFlow(byteArrayOf(0))
    override var ecgCharacteristic: StateFlow<ByteArray> = _ecgCharacteristic

    private var _bodyPosture = MutableLiveData<String>()
    override var bodyPosture: LiveData<String> = _bodyPosture

    private var _bodyTemperature = MutableLiveData<String>()
    override var bodyTemperature: LiveData<String> = _bodyTemperature


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

    override fun connectDevice(device: BluetoothDevice, context: Context) {
        connectedDevice = BleDevice(device, connectionStatus = BleConnection.DeviceConnectionLoading(device))
        bluetoothGatt = device.connectGatt(context, false, gattClientCallback)

    }

    override fun readCharacteristic(characteristic: BluetoothGattCharacteristic): Boolean {
        when (characteristic.isReadable()) {
            true -> {
                bluetoothGatt.readCharacteristic(characteristic).apply {
                    gattOperationStatus[CHAR_READ_INITIALIZED_SUCCESS] = true
                    return true
                }
            }
            else -> {
                gattOperationStatus[CHAR_READ_INITIALIZED_FAILURE] = false
                return false
            }
        }
    }

    private val gattClientCallback = object : BluetoothGattCallback() {

        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (status != BluetoothGatt.GATT_SUCCESS) {
                disconnectGattServer(gatt, BleConnection.DeviceConnectingError)
                return
            }

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                _isDeviceConnected.value = true.also {
                    Timber.d("BleManager device connected")
                }
                connectedDevice.run {
                    _connectedBleDeviceLiveData.postValue(
                        BleDevice(device, connectionStatus = BleConnection.DeviceConnected))
                }
                gatt?.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                disconnectGattServer(gatt, BleConnection.DeviceConnectingError)
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            if (status != BluetoothGatt.GATT_SUCCESS) {
                return
            }

            gatt?.let {
                it.getService(HEART_RATE_SER_UUID)?.let { heartRateService ->
                    bluetoothGattService = heartRateService
                    readCharacteristic(bluetoothGattService.getCharacteristic(HEART_RATE_CHAR_UUID))
                    Timber.d("heartrate uuid: ${heartRateService.uuid}")
                }

                it.getService(DEVICE_BATTERY_SER_UUID)?.let { batteryService ->
                    bluetoothGattService = batteryService
                    readCharacteristic(bluetoothGattService.getCharacteristic(DEVICE_BATTERY_SER_UUID))
                }

//                it.getService(BODY_POS_SER_UUID)?.let { bodyPostureService ->
//                    bluetoothGattService = bodyPostureService
//                    readCharacteristic(bluetoothGattService.getCharacteristic(BODY_POS_SER_UUID))
//                }
//
//                it.getService(TEMP_SER_UUID)?.let { tempService ->
//                    bluetoothGattService = tempService
//                    readCharacteristic(bluetoothGattService.getCharacteristic(TEMP_SER_UUID))
//                }

            }
            connectedDevice.run {
                connectedDevice =
                    BleDevice(this.device, gatt?.services,connectionStatus = BleConnection.DeviceConnected)
                _connectedBleDeviceLiveData.postValue(connectedDevice)
            }
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            val messageString: String = try {
                characteristic?.value?.convertToString() ?: ""
            } catch (e: UnsupportedEncodingException) {
                Log.e(TAG, "Unable to convert message bytes to string")
                "Error"
            }
            Log.i(TAG, "characteristic value: $messageString")
            setValueToHeartCharacteristic(characteristic)
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            Log.i(TAG, "onCharacteristicWrite status: $status")
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            if (status != BluetoothGatt.GATT_SUCCESS) {
                return
            }
            Log.i(
                TAG,
                "onCharacteristicRead status: $status, value: ${
                    String(
                        characteristic?.value ?: byteArrayOf(0)
                    )
                }"
            )
            setValueToHeartCharacteristic(characteristic)
        }
    }

    private fun setValueToHeartCharacteristic(characteristic: BluetoothGattCharacteristic?) {
        connectedDevice.let { bleDevice ->
            if (bleDevice.services?.isEmpty() == true) {
                Log.i(TAG, "No services with characteristics found.")
                return
            }

            if (bleDevice.services?.contains(bluetoothGattService) == true) {
                when(characteristic?.uuid) {
                    HEART_RATE_CHAR_UUID -> {
                        characteristic?.let {
                            _heartRateCharacteristic.value = it.value
                        }
                        setCharacteristicNotification(HEART_RATE_CHAR_UUID)
                    }
                    DEVICE_BATTERY_CHAR_UUID -> {
                        characteristic?.let {
                            _connectedBleDeviceLiveData.postValue(BleDevice(bleDevice.device, battery = it.value.last().toString(),
                                connectionStatus = BleConnection.DeviceConnected
                            ))
                        }
                        setCharacteristicNotification(DEVICE_BATTERY_CHAR_UUID)
                    }
                    ECG_RATE_CHAR_UUID -> {
                        characteristic?.let {
                            _ecgCharacteristic.value = it.value
                        }
                        setCharacteristicNotification(ECG_RATE_CHAR_UUID)
                    }
                    BODY_POS_CHAR_UUID -> {
                        characteristic?.let {
                            _bodyPosture.value = it.value.last().toString()
                        }
                        setCharacteristicNotification(BODY_POS_CHAR_UUID)
                    }
                    TEMP_CHAR_UUID -> {
                        characteristic?.let {
                            _bodyTemperature.value = it.value.last().toString()
                        }
                    }

                }
            }
            _connectedBleDeviceLiveData.postValue(
                BleDevice(bleDevice.device, connectionStatus = BleConnection.DeviceConnected))
        }
    }

    /**
     * Enables notification to start receiving updates from device
     */

    private fun setCharacteristicNotification(uuid: UUID) {

        bluetoothGatt.findCharacteristic(uuid)?.let { characteristic ->

            val payload = when {
                characteristic.isIndicatable() ->
                    BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
                characteristic.isNotifiable() ->
                    BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                else ->
                    error("${characteristic.uuid} doesn't support notifications/indications")
            }

            characteristic.getDescriptor(UUID.fromString(CCC_DESCRIPTOR_UUID))
                ?.let { cccDescriptor ->
                    if (!bluetoothGatt.setCharacteristicNotification(characteristic, true)) {
                        Timber.e("setCharacteristicNotification failed for ${characteristic.uuid}")
                        return
                    }
                    cccDescriptor.value = payload
                    bluetoothGatt.writeDescriptor(cccDescriptor)
                } ?: run {
                Timber.e("${characteristic.uuid} doesn't contain the CCC descriptor!")
            }
        }
    }

    fun disconnectGattServer(gatt: BluetoothGatt?, status: BleConnection) {
        connectedDevice.let {
            _connectedBleDeviceLiveData.postValue(BleDevice(it.device, it.services, connectionStatus = status))
        }
        _isDeviceConnected.value = false.also {
            Timber.d("BleManager device connected")
        }
        gatt?.disconnect()
        gatt?.close()
    }
}