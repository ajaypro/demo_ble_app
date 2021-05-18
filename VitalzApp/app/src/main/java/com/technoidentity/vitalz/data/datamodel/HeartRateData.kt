package com.technoidentity.vitalz.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heartrate")
data class HeartRateData(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val value: String? = null,
    val timeInterval: String? = "500ms",
    val heartRate: Int?,
    val heartRateVariability: Int?,
    val stress: Int?
) {
}