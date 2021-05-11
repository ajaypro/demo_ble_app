package com.technoidentity.vitalz.data.datamodel.otp

import com.google.gson.annotations.SerializedName

class OtpRequest {
    @SerializedName("phoneNo")
    var  phoneNo : String? = null
    @SerializedName("otp")
    var  otp : Int? = null
}