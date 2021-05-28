package com.technoidentity.vitalz.data.datamodel.multiple_patient

data class MultiplePatientDashboardResponseItem(
    val address: String,
    val age: Int,
    val bloodPressure: BloodPressure,
    val contactNumber: String,
    val device: Device,
    val doctorContactNumber: String,
    val doctorName: String,
    val emergencyContactName: String,
    val emergencyContactNumber: String,
    val gender: String,
    val heartRate: HeartRate,
    val height: Double,
    val hospitalId: String,
    val name: String,
    val otherHealthIssues: String,
    val oxygenSaturation: OxygenSaturation,
    val patientId: String,
    val posture: Posture,
    val respiratoryRate: RespiratoryRate,
    val step: Step,
    val temprature: Temprature,
    val weight: Weight
)