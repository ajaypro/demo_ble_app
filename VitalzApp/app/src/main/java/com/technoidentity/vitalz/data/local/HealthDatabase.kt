package com.technoidentity.vitalz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technoidentity.vitalz.data.datamodel.HeartRate

@Database(entities = [(HeartRate::class)], version = 1)
abstract class HealthDatabase: RoomDatabase() {

}