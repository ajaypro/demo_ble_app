package com.technoidentity.vitalz.data.datamodel.docNurseLogin

import com.google.gson.annotations.SerializedName

data class DocNurseResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
)