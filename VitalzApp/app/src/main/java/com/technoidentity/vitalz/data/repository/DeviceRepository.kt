package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice

interface DeviceRepository {

    suspend fun sendDeviceWithMacId(deviceMac: BleMac): RegisteredDevice

    suspend fun getRegisteredDevices(): List<RegisteredDevice>

    suspend fun sendHeartRate(patientId: String, telemetryKey: String,heartRate: ByteArray): Boolean

}