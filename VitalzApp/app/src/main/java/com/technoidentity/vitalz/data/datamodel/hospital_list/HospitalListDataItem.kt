package com.technoidentity.vitalz.data.datamodel.hospital_list

import com.google.gson.annotations.SerializedName

data class HospitalListDataItem(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("hospitalName")
    val hospitalName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("status")
    val status: Boolean?
)