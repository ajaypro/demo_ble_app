package com.technoidentity.vitalz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technoidentity.vitalz.data.local.dao.EcgDataDao
import com.technoidentity.vitalz.data.local.dao.HeartRateDao
import com.technoidentity.vitalz.data.local.databaseEntities.EcgDataDb
import com.technoidentity.vitalz.data.local.databaseEntities.HeartRateDb
import javax.inject.Singleton

@Singleton
@Database(entities = [HeartRateDb::class, EcgDataDb::class], version = 1, exportSchema = false)
abstract class HealthDatabase : RoomDatabase() {

   abstract val heartRateDao: HeartRateDao

   abstract val ecgDataDao: EcgDataDao

}