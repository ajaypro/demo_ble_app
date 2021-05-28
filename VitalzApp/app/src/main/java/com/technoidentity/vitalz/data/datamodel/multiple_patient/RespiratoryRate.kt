package com.technoidentity.vitalz.data.datamodel.multiple_patient

data class RespiratoryRate(
    val date: String,
    val ratePerMinute: List<Int>
)