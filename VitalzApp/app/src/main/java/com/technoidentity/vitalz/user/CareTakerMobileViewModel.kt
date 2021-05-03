package com.technoidentity.vitalz.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technoidentity.vitalz.data.repository.UserRepository

class CareTakerMobileViewModel(private val userRepository : UserRepository) : ViewModel() {

    fun loginApi(mobileNumber: String) {
//        userRepository.doMobileOTPCall(mobileNumber)
        Log.d("check Mobile ", mobileNumber)
    }

}
