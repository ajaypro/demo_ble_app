package com.technoidentity.vitalz.data.datamodel.otp

import com.google.gson.annotations.SerializedName

data class OtpResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
)