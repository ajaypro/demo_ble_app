package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.local.databaseEntities.EcgDataDb
import com.technoidentity.vitalz.data.local.databaseEntities.HeartRateDb
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    suspend fun sendDeviceWithMacId(deviceMac: BleMac): RegisteredDevice

    suspend fun getRegisteredDevices(): List<RegisteredDevice>

    suspend fun sendHeartRate(patientId: String, telemetryKey: String, heartRate: String): Boolean

    suspend fun insertHeartRateDb(heartRateDb: HeartRateDb)

    suspend fun sendEcgData(patientId: String, telemetryKey: String, ecgData: String): Boolean

    suspend fun insertEcgDataDb(ecgDataDb: EcgDataDb)

    suspend fun getHeartRateDb(): Flow<HeartRateDb>

    suspend fun deleteHeartRateData(heartRateDb: HeartRateDb)

    suspend fun deleteEcgData(ecgDataDb: EcgDataDb)

    suspend fun getEcgDataDb(): Flow<EcgDataDb>

}