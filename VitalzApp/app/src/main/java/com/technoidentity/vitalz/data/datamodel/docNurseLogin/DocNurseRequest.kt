package com.technoidentity.vitalz.data.datamodel.docNurseLogin

import com.google.gson.annotations.SerializedName

class DocNurseRequest {
  @SerializedName("userId")
  var  username : String? = null

  @SerializedName("password")
  var  password : String? = null
}