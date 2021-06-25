package com.technoidentity.vitalz.data.datamodel.multiple_patient

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MultiplePatientDashboardResponse(
    val multiPatientDashboardList: List<MultiplePatientDashboardResponseItem>,

    @Expose
    @SerializedName("reason")
    val reason: String? = null
) : Parcelable