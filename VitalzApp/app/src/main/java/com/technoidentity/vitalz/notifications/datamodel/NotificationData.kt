package com.technoidentity.vitalz.notifications.datamodel

data class NotificationData(
    val title: String,
    val message: VitalzMessage,
)

data class VitalzMessage(
    val patchId: String,
    val telemetryKey: String,
    val telemetryValue: String
)
