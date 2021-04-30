package com.technoidentity.vitalz.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CareTakerMobileViewModel: ViewModel() {

    lateinit var careTakerLoginInterface : CareTakerLoginInterface
    var mobileNumber : MutableLiveData<String> = MutableLiveData()

    fun setInterface(careTakerLoginInterface: CareTakerLoginInterface){
        this.careTakerLoginInterface = careTakerLoginInterface
    }

    fun loginApi(mobileNumber : String){
        Log.d("check Mobile ", mobileNumber)
    }

}
