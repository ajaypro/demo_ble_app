package com.technoidentity.vitalz.data.datamodel.multiple_patient

data class OxygenSaturation(
    val date: String,
    val oxygenPercentage: List<Double>
)