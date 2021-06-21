package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.bluetooth.data.RegisteredDeviceList
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.utils.ResultHandler
import com.technoidentity.vitalz.utils.ResultHandler.Error
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(private val api: VitalzApi): DeviceRepository {

    override suspend fun sendDeviceWithMacId(deviceMac: BleMac): RegisteredDevice {
        return kotlin.runCatching {
            api.sendDevicesForRegisteration(deviceMac)
        }.getOrThrow()
    }

    override suspend fun getRegisteredDevices(): List<RegisteredDevice> {
        return kotlin.runCatching {
            api.getRegisteredDevices()
        }.getOrThrow()
    }

}


