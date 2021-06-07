package com.technoidentity.vitalz.data.datamodel.patient_list

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
):Parcelable