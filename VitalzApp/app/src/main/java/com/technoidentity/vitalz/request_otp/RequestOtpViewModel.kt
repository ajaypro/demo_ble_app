package com.technoidentity.vitalz.request_otp

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RequestOtpViewModel : ViewModel() {

  private lateinit var requestOtpInterface: RequestOtpInterface
  var mobileNumber : MutableLiveData<String> = MutableLiveData()

  fun setInterface(requestOtpInterface: RequestOtpInterface){
    this.requestOtpInterface = requestOtpInterface
  }

  fun onRequestOtp(view : View){
    //before Api
    requestOtpInterface.navigateToActivity()
  }
}