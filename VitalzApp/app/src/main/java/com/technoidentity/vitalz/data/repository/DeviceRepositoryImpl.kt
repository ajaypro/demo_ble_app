package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.network.VitalzApi
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(private val api: VitalzApi): DeviceRepository {

    override suspend fun sendDeviceWithMacId(deviceMac: BleMac): RegisteredDevice {
        return kotlin.runCatching {
            api.sendDevicesForRegisteration(deviceMac)
        }.getOrElse {
            RegisteredDevice(error = it.message.toString())
        }
    }

    override suspend fun getRegisteredDevices(): List<RegisteredDevice> {
        return kotlin.runCatching {
            api.getRegisteredDevices()
        }.getOrElse {
            listOf(RegisteredDevice(error = it.message.toString()))
        }
    }

    override suspend fun sendHeartRate(patientId: String, telemetryKey: String, heartRate: ByteArray): Boolean {
        return kotlin.runCatching {
            api.sendHeartRate(patientId, telemetryKey, heartRate)
        }.getOrElse {
            false
        }
    }
}


