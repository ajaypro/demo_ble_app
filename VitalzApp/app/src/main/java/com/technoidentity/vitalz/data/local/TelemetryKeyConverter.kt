/*
package com.technoidentity.vitalz.data.local

import androidx.room.TypeConverter
import com.technoidentity.vitalz.utils.TelemetryKey

class TelemetryKeyConverter {

    @TypeConverter
    fun fromTelemetryKey(value: TelemetryKey): String {
        return value.name
    }

    fun toTelemetryKey(value: String): TelemetryKey {
         return when (value) {
              "heartrate" -> TelemetryKey.HEART_RATE_DATA
             "battery" -> TelemetryKey.BATTERY_DATA
             else -> TelemetryKey.ECG_DATA
         }
    }
}*/
