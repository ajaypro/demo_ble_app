package com.technoidentity.vitalz.home

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import android.content.Context
import androidx.lifecycle.*
import com.technoidentity.vitalz.bluetooth.connection.IBleManager
import com.technoidentity.vitalz.bluetooth.data.BleDevice
import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.local.databaseEntities.HeartRateDb
import com.technoidentity.vitalz.data.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * This will be used for singlepatient or multipledashbaord
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val bleManager: IBleManager,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    /**
     * Device connection livedata and functions
     */

    val connectedDeviceData: LiveData<BleDevice> = bleManager.connectedBleDeviceLiveData

    //To call in activity or fragment to dispaly device connectivity common to all screens
    var isDeviceConnected: LiveData<Boolean> = bleManager.isDeviceConnected.asLiveData().also {
        Timber.d("homeviewmodel ${it.value}")
    }

    var scanFlow: Flow<BluetoothDevice> = bleManager.scanChannel.receiveAsFlow()

    var deviceBattery: LiveData<Int> = bleManager.battery

    var isScanning = bleManager.isScanning.asLiveData()

    fun startScan() = bleManager.startScan()

    fun toggleScan() {
        bleManager.apply { if (isScanning.value) stopScan() else startScan() }
    }

    fun stopScan() = bleManager.stopScan()

    fun connectDevice(device: BluetoothDevice, context: Context) {

        bleManager.connectDevice(device, context)
    }

    var registeredDevice: RegisteredDevice? = null


    fun readCharacteristics(device: BluetoothDevice, uuid: UUID, service: BluetoothGattService) {
        viewModelScope.launch(Dispatchers.IO) {
            bleManager.readCharacteristic(device, uuid, service)
        }
    }

    fun enableNotifications(device: BluetoothDevice, characteristic: BluetoothGattCharacteristic) {
        bleManager.enableNotifications(device, characteristic)
    }

    fun deviceForRegisteration(deviceMacID: BleMac): LiveData<RegisteredDevice> {

        return liveData {
            withContext(Dispatchers.IO) {
                deviceRepository.getRegisteredDevice(deviceMacID).apply {
                    withContext(Dispatchers.Main) {
                        emit(this@apply)
                        registeredDevice = this@apply
                        Timber.i("sharevm ${this@apply.patchId} ${this@apply.macId}")
                    }
                }
            }
        }
    }

    fun registeredDevice(device: BluetoothDevice): Triple<Boolean, String?, String?> {

        registeredDevice?.let {
            return if (it.macId == device.address) {
                Triple(true, it.patchId, it.macId)
            } else {
                Triple(false, device.name, device.address)
            }
        }
        return Triple(false, device.name, device.address)
    }

    /**
     * Patient data livedata and functions
     */
    var heartRateData: Flow<ByteArray> = bleManager.heartRateCharacteristic

    var dataToServer by Delegates.notNull<Boolean>()

    fun sendHeartRateToServer(patientId: String, telemetryKey: String, heartRate: List<Byte>) {

        viewModelScope.launch {

            while (isActive) {
                // Data is failed to send to server
                deviceRepository.getHeartRateDb().collect { heartRateDb ->
                    senData(
                        heartRateDb.patientId,
                        heartRateDb.telemetryKey,
                        heartRateDb.heartRateValue
                    ).apply {
                        require(this) {
                            deviceRepository.deleteHeartRateData(heartRateDb)
                        }
                    }
                }
                senData(patientId, telemetryKey, heartRate)

                delay(10000L)
            }
        }
    }

    private suspend fun senData(
        patientId: String,
        telemetryKey: String,
        heartRateValue: List<Byte>
    ): Boolean {

        deviceRepository.sendHeartRate(patientId, telemetryKey, heartRateValue).apply {
            return if (!this) {
                dataToServer = false
                deviceRepository.insertHeartRateDb(
                    HeartRateDb(
                        patientId = patientId,
                        telemetryKey = telemetryKey,
                        heartRateValue = heartRateValue
                    )
                )
                //start service to again send data in api
                // if returns true from api data
                //database.delete(heartRate)
                false
            } else {
                dataToServer = this // data sent to server
                true
            }
        }

    }

    var ecgData: Flow<ByteArray> = bleManager.ecgCharacteristic

    val bodyTemperature: LiveData<String> = bleManager.bodyTemperature

    val bodyPosture: LiveData<String> = bleManager.bodyPosture

    //Check User Selected What
    private var _isCareTaker = MutableLiveData(false)
    val isSelected: LiveData<Boolean> = _isCareTaker

    fun isCareTakerSelected(selected: Boolean) {
        _isCareTaker.value = selected
    }

    //check the role of User
    private var _role = MutableStateFlow("Un-Authorized")
    val assignedRole: MutableStateFlow<String> = _role

    fun checkRole(role: String) {
        _role.value = role
    }

    //check the Notification Count of User
    var notificationCount = MutableStateFlow(0)
}


