package com.technoidentity.vitalz.data.datamodel.careTakerLogin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CareTakerRequest (
  @SerializedName("phoneNo")
  var  phoneNo : String? = null
): Parcelable