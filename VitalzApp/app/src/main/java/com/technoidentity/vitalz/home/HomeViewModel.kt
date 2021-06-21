package com.technoidentity.vitalz.home

import android.bluetooth.*
import android.content.Context
import androidx.lifecycle.*
import com.technoidentity.vitalz.bluetooth.IBleManager
import com.technoidentity.vitalz.bluetooth.data.BleDevice
import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.repository.DeviceRepository
import com.technoidentity.vitalz.utils.DEVICE_START_NAME
import com.technoidentity.vitalz.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bleManager: IBleManager,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    //use to call in fragment such as singlepatient or multipledashbaord
    val connectedDevice: LiveData<BleDevice> = bleManager.connectedBleDeviceLiveData

    //ask sneha about the attribute in characteristic which gives ascii value
    //private var _heartRateCharacteristic = MutableStateFlow(0)
    var heartRateCharacteristic: Flow<ByteArray> = bleManager.heartRateCharacteristic

    //To call in activity or fragment to dispaly device connectivity common to all screens
    var isDeviceConnected: Flow<Boolean> = bleManager.isDeviceConnected.also {
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

//     suspend fun checkDeviceRegistered(devices: List<BluetoothDevice>): List<BluetoothDevice> {
//         val hrmDevices = devices.filter { it.name.startsWith(DEVICE_START_NAME) }
//         var registeredDevices = deviceRepository.getRegisteredDevices()
//
//         return hrmDevices.filter { bleDevice ->
//             registeredDevices.any { registeredDevice ->
//                 registeredDevice.macId == bleDevice.address }
//         }.onEach { bluetoothDevice ->
//             registeredDevices.onEach {
//                 bluetoothDevice.name = it.patchId
//             }
//         }
//
//     }
}


