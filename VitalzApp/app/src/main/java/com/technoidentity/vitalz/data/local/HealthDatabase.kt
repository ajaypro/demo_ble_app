package com.technoidentity.vitalz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.technoidentity.vitalz.data.local.dao.EcgDataDao
import com.technoidentity.vitalz.data.local.dao.HeartRateDao
import com.technoidentity.vitalz.data.local.dao.RegisteredDeviceDao
import com.technoidentity.vitalz.data.local.databaseEntities.EcgDataDb
import com.technoidentity.vitalz.data.local.databaseEntities.HeartRateDb
import com.technoidentity.vitalz.data.local.databaseEntities.RegisteredDeviceDb
import javax.inject.Singleton

@Database(entities = [HeartRateDb::class, EcgDataDb::class, RegisteredDeviceDb::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HealthDatabase : RoomDatabase() {

   abstract val heartRateDao: HeartRateDao

   abstract val ecgDataDao: EcgDataDao

   abstract val registeredDeviceDao: RegisteredDeviceDao

}