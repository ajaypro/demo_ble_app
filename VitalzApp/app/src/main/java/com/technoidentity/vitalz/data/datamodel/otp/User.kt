package com.technoidentity.vitalz.data.datamodel.otp

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneNo")
    val phoneNo: String?,
)