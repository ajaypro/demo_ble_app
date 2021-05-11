package com.technoidentity.vitalz.data.datamodel.login

import com.google.gson.annotations.SerializedName

class CareTakerOtpResponse {
    @SerializedName("reason")
    var reason: String? = null
    @SerializedName("success")
    var success: Boolean? = null
}