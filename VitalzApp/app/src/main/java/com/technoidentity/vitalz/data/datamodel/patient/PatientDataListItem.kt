package com.technoidentity.vitalz.data.datamodel.patient

import com.google.gson.annotations.SerializedName

data class PatientDataListItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)