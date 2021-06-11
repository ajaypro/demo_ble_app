package com.technoidentity.vitalz.data.datamodel.notification

import com.google.gson.annotations.SerializedName

class NotificationRequest {
    @SerializedName("userId")
    var userId: String? = null
}