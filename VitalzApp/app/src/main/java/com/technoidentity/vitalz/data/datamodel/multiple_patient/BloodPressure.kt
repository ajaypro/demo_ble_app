package com.technoidentity.vitalz.data.datamodel.multiple_patient

data class BloodPressure(
    val date: String,
    val diastolicPressure: List<Int>,
    val systolicPressure: List<Int>
)