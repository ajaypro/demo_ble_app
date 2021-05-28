package com.technoidentity.vitalz.data.datamodel.multiple_patient

import com.google.gson.annotations.SerializedName

data class MultiplePatientDashboardResponseItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("bloodPressure")
    val bloodPressure: BloodPressure,
    @SerializedName("contactNumber")
    val contactNumber: String,
    @SerializedName("device")
    val device: Device,
    @SerializedName("doctorContactNumber")
    val doctorContactNumber: String,
    @SerializedName("doctorName")
    val doctorName: String,
    @SerializedName("emergencyContactName")
    val emergencyContactName: String,
    @SerializedName("emergencyContactNumber")
    val emergencyContactNumber: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("heartRate")
    val heartRate: HeartRate,
    @SerializedName("height")
    val height: Double,
    @SerializedName("hospitalId")
    val hospitalId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("otherHealthIssues")
    val otherHealthIssues: String,
    @SerializedName("oxygenSaturation")
    val oxygenSaturation: OxygenSaturation,
    @SerializedName("patientId")
    val patientId: String,
    @SerializedName("posture")
    val posture: Posture,
    @SerializedName("respiratoryRate")
    val respiratoryRate: RespiratoryRate,
    @SerializedName("step")
    val step: Step,
    @SerializedName("temprature")
    val temperature: Temperature,
    @SerializedName("weight")
    val weight: Weight
)