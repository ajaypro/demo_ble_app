package com.technoidentity.vitalz.home

import android.bluetooth.BluetoothDevice
import android.content.Context
import androidx.lifecycle.*
import com.technoidentity.vitalz.bluetooth.connection.IBleManager
import com.technoidentity.vitalz.bluetooth.data.BleDevice
import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * This will be used for singlepatient or multipledashbaord
 */
@HiltViewModel
class SharedViewModel @Inject constructor(private val bleManager: IBleManager,
                                          private val deviceRepository: DeviceRepository) : ViewModel() {

    /**
     * Device connection livedata and functions
     */

    val connectedDevice: LiveData<BleDevice> = bleManager.connectedBleDeviceLiveData

    //To call in activity or fragment to dispaly device connectivity common to all screens
    var isDeviceConnected: LiveData<Boolean> = bleManager.isDeviceConnected.asLiveData().also {
        Timber.d("homeviewmodel ${it.value}")
    }

    var scanFlow: Flow<BluetoothDevice> = bleManager.scanChannel.receiveAsFlow()

    var isScanning = bleManager.isScanning.asLiveData()

    fun startScan() = bleManager.startScan()

    fun toggleScan() {
        bleManager.apply { if (isScanning.value) stopScan() else startScan() }
    }

    fun connectDevice(device: BluetoothDevice, context: Context) {
        bleManager.connectDevice(device, context)
    }

    var registeredDevice: RegisteredDevice? = null


    fun sendDeviceForRegisteration(deviceMacID: BleMac): LiveData<RegisteredDevice> {
        return liveData {
            emit(deviceRepository.sendDeviceWithMacId(deviceMacID).also {
                registeredDevice = it
            })
        }
    }

    fun registeredDevice(device: BluetoothDevice):Triple<Boolean, String?, String?>{

        registeredDevice?.let {
            return if(it.macId == device.address) {
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

    fun sendHeartRateToServer(patientId: String, telemetryKey: String, heartRate: ByteArray) {
        viewModelScope.launch {
            deviceRepository.sendHeartRate(patientId, telemetryKey, heartRate)
            // setup table and store values in table
        }
    }

    var ecgData: Flow<ByteArray>  = bleManager.ecgCharacteristic

    val bodyTemperature: LiveData<String> = bleManager.bodyTemperature

    val bodyPosture: LiveData<String> = bleManager.bodyPosture

    //Check User Selected What
    private var _isCareTaker = MutableStateFlow(false)
    val isSelected: StateFlow<Boolean> = _isCareTaker

    fun isCareTakerSelected(selected : Boolean){
        _isCareTaker.value = selected
    }


}


