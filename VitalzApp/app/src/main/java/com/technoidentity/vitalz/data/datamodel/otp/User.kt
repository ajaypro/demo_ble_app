package com.technoidentity.vitalz.data.datamodel.otp

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneExt")
    val phoneExt: String,
    @SerializedName("phoneNo")
    val phoneNo: String,
    @SerializedName("role")
    val role: String
)