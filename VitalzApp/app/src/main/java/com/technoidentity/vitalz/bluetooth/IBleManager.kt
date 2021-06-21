package com.technoidentity.vitalz.bluetooth

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import androidx.lifecycle.LiveData
import com.technoidentity.vitalz.bluetooth.data.BleDevice
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

@ViewModelScoped
interface IBleManager {

        fun startScan()
        fun stopScan()
        var isScanning: StateFlow<Boolean>
        var heartRateCharacteristic: StateFlow<ByteArray>
        var connectedBleDeviceLiveData: LiveData<BleDevice>
        var isDeviceConnected: StateFlow<Boolean>
        var scanChannel: Channel<BluetoothDevice>
        fun connectDevice(device: BluetoothDevice, context: Context)
        fun readCharacteristic(characteristic: BluetoothGattCharacteristic)
    }
