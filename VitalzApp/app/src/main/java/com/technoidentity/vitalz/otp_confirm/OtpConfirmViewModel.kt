package com.technoidentity.vitalz.otp_confirm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtpConfirmViewModel : ViewModel() {

    lateinit var otpConfirmInterface: OtpConfirmInterface
    var mobileNumber: MutableLiveData<String> = MutableLiveData()
    var otp1: MutableLiveData<String> = MutableLiveData()
    var otp2: MutableLiveData<String> = MutableLiveData()
    var otp3: MutableLiveData<String> = MutableLiveData()
    var otp4: MutableLiveData<String> = MutableLiveData()
    var otp5: MutableLiveData<String> = MutableLiveData()
    var otp6: MutableLiveData<String> = MutableLiveData()

    fun setInterface(otpConfirmInterface: OtpConfirmInterface) {
        this.otpConfirmInterface = otpConfirmInterface
    }

    fun onConfirmOtp(view: View) {
        otpConfirmInterface.navigateToActivity()
    }
}