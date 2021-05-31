package com.technoidentity.vitalz.data.datamodel.careTakerLogin

import com.google.gson.annotations.SerializedName

class CareTakerOtpResponse {
    @SerializedName("reason")
    var reason: String? = null
    @SerializedName("success")
    var success: Boolean? = null
}