package com.technoidentity.vitalz.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technoidentity.vitalz.bluetooth.SingletonHolder
import com.technoidentity.vitalz.data.datamodel.HeartRateData

@Database(entities = [(HeartRateData::class)], version = 1, exportSchema = false)
abstract class HealthDatabase : RoomDatabase() {

    companion object: SingletonHolder<HealthDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, HealthDatabase::class.java, "vitalz.db")
            .build()
    })

}