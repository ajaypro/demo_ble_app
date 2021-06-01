package com.technoidentity.vitalz.data.datamodel.patient_list

import com.google.gson.annotations.SerializedName

data class PatientDataListItem(
    @SerializedName("address")
    val address: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("patientId")
    val id: String?,
    @SerializedName("name")
    val name: String?
)