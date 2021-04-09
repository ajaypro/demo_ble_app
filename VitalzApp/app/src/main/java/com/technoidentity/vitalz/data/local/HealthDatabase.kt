package com.technoidentity.vitalz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technoidentity.vitalz.data.datamodel.HeartRateData

@Database(entities = [(HeartRateData::class)], version = 1, exportSchema = false)
abstract class HealthDatabase: RoomDatabase() {

}