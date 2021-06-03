package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SinglePatientDashboardResponse(
    @SerializedName("bloodPressure")
    val bloodPressure: BloodPressure,
    @SerializedName("contactNumber")
    val contactNumber: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("device")
    val device: Device,
    @SerializedName("emailId")
    val emailId: String,
    @SerializedName("heartRate")
    val heartRate: HeartRate,
    @SerializedName("height")
    val height: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("oxygenSaturation")
    val oxygenSaturation: OxygenSaturation,
    @SerializedName("posture")
    val posture: Posture,
    @SerializedName("respiratoryRate")
    val respiratoryRate: RespiratoryRate,
    @SerializedName("sleep")
    val sleep: Sleep,
    @SerializedName("step")
    val step: Step,
    @SerializedName("temprature")
    val temperature: Temperature,
    @SerializedName("weight")
    val weight: Weight
):Parcelable