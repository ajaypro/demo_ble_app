package com.technoidentity.vitalz.data.datamodel.docNurseLogin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DocNurseRequest (
  @SerializedName("userId")
  var  username : String? = null,

  @SerializedName("password")
  var  password : String? = null
):Parcelable