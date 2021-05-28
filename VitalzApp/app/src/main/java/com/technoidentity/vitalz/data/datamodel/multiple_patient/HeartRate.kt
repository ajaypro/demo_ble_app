package com.technoidentity.vitalz.data.datamodel.multiple_patient

data class HeartRate(
    val date: String,
    val ratePerMinute: List<Int>
)