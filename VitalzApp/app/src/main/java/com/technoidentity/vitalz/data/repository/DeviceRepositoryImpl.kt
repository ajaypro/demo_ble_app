package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.local.dao.EcgDataDao
import com.technoidentity.vitalz.data.local.dao.HeartRateDao
import com.technoidentity.vitalz.data.local.databaseEntities.EcgDataDb
import com.technoidentity.vitalz.data.local.databaseEntities.HeartRateDb
import com.technoidentity.vitalz.data.network.VitalzApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(private val api: VitalzApi,
private val heartRateDao: HeartRateDao, private val ecgDataDao: EcgDataDao): DeviceRepository {

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

    override suspend fun sendHeartRate(patientId: String, telemetryKey: String, heartRate: String): Boolean {
        return kotlin.runCatching {
            api.sendHeartRate(patientId, telemetryKey, heartRate)
        }.getOrElse {
            false
        }
    }

    override suspend fun sendEcgData(patientId: String, telemetryKey: String, ecgData: String): Boolean {
        return kotlin.runCatching {
            api.sendEcgData(patientId, telemetryKey, ecgData)
        }.getOrElse {
            false
        }
    }

    override suspend fun getHeartRateDb(): Flow<HeartRateDb> = heartRateDao.getHeartRate()

    override suspend fun getEcgDataDb(): Flow<EcgDataDb>  = ecgDataDao.getEcgData()

    override suspend fun insertHeartRateDb(heartRateDb: HeartRateDb) {
        heartRateDao.insert(heartRateDb)
    }

    override suspend fun insertEcgDataDb(ecgDataDb: EcgDataDb) {
        ecgDataDao.insert(ecgDataDb)
    }

    override suspend fun deleteHeartRateData(heartRateDb: HeartRateDb) {
        heartRateDao.delete(heartRateDb)
    }

    override suspend fun deleteEcgData(ecgDataDb: EcgDataDb) {
        ecgDataDao.delete(ecgDataDb)
    }
}


