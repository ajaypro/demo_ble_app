package com.technoidentity.vitalz.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareTakerMobileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _expectedResult = MutableLiveData<CareTakerOtpResponse>()
    val expectedResult: LiveData<CareTakerOtpResponse> = _expectedResult

    fun getCareTakerResponse(mobile: String) {
        val request = CareTakerRequest()
        request.phoneNo = mobile
        viewModelScope.launch {
            userRepository.doMobileOTPCall(request).apply {
                if (this.success){
                    _expectedResult.value = this
                }else{
                    _expectedResult.value = this
                }
            }
        }
    }
}
