package com.technoidentity.vitalz.data.datamodel.patient_list

import com.google.gson.annotations.SerializedName

class PatientRequest {
    @SerializedName("hospitalId")
    var hospitalId: String? = null
    @SerializedName("phoneNo")
    var phoneNo: String? = null
}