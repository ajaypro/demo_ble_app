package com.technoidentity.vitalz.data.datamodel.hospital

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: Any,
    @SerializedName("zipCode")
    val zipCode: String
)