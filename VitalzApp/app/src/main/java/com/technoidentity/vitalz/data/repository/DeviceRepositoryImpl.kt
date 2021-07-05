package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.local.dao.EcgDataDao
import com.technoidentity.vitalz.data.local.dao.HeartRateDao
import com.technoidentity.vitalz.data.local.dao.RegisteredDeviceDao
import com.technoidentity.vitalz.data.local.databaseEntities.EcgDataDb
import com.technoidentity.vitalz.data.local.databaseEntities.HeartRateDb
import com.technoidentity.vitalz.data.local.databaseEntities.RegisteredDeviceDb
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.notifications.datamodel.VitalzTelemetryNotification
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val api: VitalzApi,
    private val heartRateDao: HeartRateDao, private val ecgDataDao: EcgDataDao,
    private val registeredDeviceDao: RegisteredDeviceDao
) : DeviceRepository {

    override suspend fun getRegisteredDevice(deviceMac: BleMac): RegisteredDevice {
        return kotlin.runCatching {
            when (isDeviceExist(deviceMac.macId)){
                false -> {
                    api.sendDevicesForRegisteration(deviceMac).apply {
                        insertRegisteredDeviceDb(RegisteredDeviceDb(patchId = this.patchId, macId = this.macId)).also {
                            Timber.d("Device registered in backend and local db")
                        }
                    }
                }
                true -> {
                    with(getRegsteredDeviceDb()){
                        RegisteredDevice(patchId, macId)
                    }
                }
            }
        }.getOrElse {
            RegisteredDevice(message = "Something went wrong device not available")
        }
    }


    override suspend fun getRegisteredDevices(): List<RegisteredDevice> {
        return kotlin.runCatching {
            api.getRegisteredDevices()
        }.getOrElse {
            listOf(RegisteredDevice(message = it.message.toString()))
        }
    }

    override suspend fun sendHeartRate(
        patientId: String,
        telemetryKey: String,
        heartRate: List<Byte>
    ): Boolean {
        return kotlin.runCatching {
            api.sendHeartRate(patientId, telemetryKey, heartRate)
        }.getOrElse {
            false
        }
    }

    override suspend fun sendEcgData(
        patientId: String,
        telemetryKey: String,
        ecgData: List<Byte>
    ): Boolean {
        return kotlin.runCatching {
            api.sendEcgData(patientId, telemetryKey, ecgData)
        }.getOrElse {
            false
        }
    }

    override suspend fun sendTelemetryNotification(vitalz: VitalzTelemetryNotification): Boolean {
        return kotlin.runCatching {
            api.sendTelemetryNotification(vitalz)
        }.getOrElse {
            false
        }
    }

    override suspend fun getHeartRateDb(): Flow<HeartRateDb> = heartRateDao.getHeartRate()

    override suspend fun getEcgDataDb(): Flow<EcgDataDb> = ecgDataDao.getEcgData()

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

    override suspend fun insertRegisteredDeviceDb(registeredDeviceDb: RegisteredDeviceDb) {
        registeredDeviceDao.insert(registeredDeviceDb)
    }

    override suspend fun getRegsteredDeviceDb(): RegisteredDeviceDb =
        registeredDeviceDao.registeredDeviceDb()

    override fun isDeviceExist(macId: String): Boolean =
        registeredDeviceDao.checkDeviceMacId(macId)
}


