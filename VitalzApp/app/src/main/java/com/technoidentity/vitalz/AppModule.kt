package com.technoidentity.vitalz

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.ParcelUuid
import androidx.room.Room
import com.technoidentity.vitalz.bluetooth.connection.BleManager
import com.technoidentity.vitalz.bluetooth.connection.BleScanner
import com.technoidentity.vitalz.bluetooth.connection.IBleManager
import com.technoidentity.vitalz.data.local.HealthDatabase
import com.technoidentity.vitalz.data.local.dao.EcgDataDao
import com.technoidentity.vitalz.data.local.dao.HeartRateDao
import com.technoidentity.vitalz.data.local.dao.RegisteredDeviceDao
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.data.network.VitalzService
import com.technoidentity.vitalz.data.repository.DeviceRepository
import com.technoidentity.vitalz.data.repository.DeviceRepositoryImpl
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.data.repository.UserRepositoryImpl
import com.technoidentity.vitalz.utils.Constants
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesVitalzApi() : VitalzApi = VitalzService.getRestApi()!!

    @Singleton
    @Provides
    fun providesMainRepository(api: VitalzApi): UserRepository = UserRepositoryImpl(api)

    @Singleton
    @Provides
    fun providesDeviceRepository(api: VitalzApi, heartRateDao: HeartRateDao,
    ecgDataDao: EcgDataDao, registeredDeviceDao: RegisteredDeviceDao): DeviceRepository = DeviceRepositoryImpl(api,heartRateDao, ecgDataDao, registeredDeviceDao)

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutinesDispatcherProvider = object : CoroutinesDispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun providesBluetoothScanner(): BluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner

    @Singleton
    @Provides
    fun providesScanFilter() = listOf(ScanFilter.Builder()
                .setServiceUuid(ParcelUuid.fromString(Constants.SERVICE_UUID))
                .build())

    @Singleton
    @Provides
    fun providesScanSettings() = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    @Singleton
    @Provides
    fun providesBleScanner(bluetoothLeScanner: BluetoothLeScanner, scanFilter: List<ScanFilter>, scanSettings: ScanSettings): BleScanner =
         BleScanner(bluetoothLeScanner, scanFilter, scanSettings)


    @Singleton
    @Provides
    fun providesIbleManager(bleScanner: BleScanner, api: VitalzApi): IBleManager = BleManager(bleScanner, api)

    @Singleton
    @Provides
    fun provideHealthDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(app, HealthDatabase::class.java, "vitalzdb")
        .build()

    @Singleton
    @Provides
    fun provideHeartRateDao(healthDatabase: HealthDatabase) = healthDatabase.heartRateDao

    @Singleton
    @Provides
    fun provideEcgRateDao(healthDatabase: HealthDatabase) = healthDatabase.ecgDataDao

    @Singleton
    @Provides
    fun provideRegisteredDao(healthDatabase: HealthDatabase) = healthDatabase.registeredDeviceDao


}