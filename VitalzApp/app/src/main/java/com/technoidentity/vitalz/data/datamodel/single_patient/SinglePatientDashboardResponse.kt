package com.technoidentity.vitalz.data.datamodel.single_patient

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SinglePatientDashboardResponse(
    @Expose
    @SerializedName("bloodPressure")
    val bloodPressure: BloodPressure,
    @Expose
    @SerializedName("contactNumber")
    val contactNumber: String,
    @Expose
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @Expose
    @SerializedName("device")
    val device: Device,
    @Expose
    @SerializedName("emailId")
    val emailId: String,
    @Expose
    @SerializedName("heartRate")
    val heartRate: HeartRate,
    @Expose
    @SerializedName("height")
    val height: Double,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("oxygenSaturation")
    val oxygenSaturation: OxygenSaturation,
    @Expose
    @SerializedName("posture")
    val posture: Posture,
    @Expose
    @SerializedName("respiratoryRate")
    val respiratoryRate: RespiratoryRate,
    @Expose
    @SerializedName("sleep")
    val sleep: Sleep,
    @Expose
    @SerializedName("step")
    val step: Step,
    @Expose
    @SerializedName("temprature")
    val temperature: Temperature,
    @Expose
    @SerializedName("weight")
    val weight: Weight
):Parcelable