package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.bluetooth.data.RegisteredDeviceList
import com.technoidentity.vitalz.utils.ResultHandler

interface DeviceRepository {

    suspend fun sendDeviceWithMacId(deviceMac: BleMac): RegisteredDevice

    suspend fun getRegisteredDevices(): List<RegisteredDevice>

}