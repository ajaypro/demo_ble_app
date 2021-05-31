package com.technoidentity.vitalz.user

import androidx.lifecycle.*
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpMobileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class OtpResponse {
        class Success(val resultText: String, val data: com.technoidentity.vitalz.data.datamodel.otp.OtpResponse?) : OtpResponse()
        class Failure(val errorText: String) : OtpResponse()
        object Loading : OtpResponse()
        object Empty : OtpResponse()
    }

    private val _expectedResult = MutableLiveData<OtpResponse>(OtpResponse.Empty)
    val expectedResult: LiveData<OtpResponse> = _expectedResult

    fun getOtpResponse(mobile: String?, otpReceived: Int) {
        if (mobile == null && otpReceived == null) {
            _expectedResult.value = OtpResponse.Failure("Not a Valid Number")
            return
        }
        val request = OtpRequest()
        request.phoneNo = mobile
        request.otp = otpReceived
        viewModelScope.launch(dispatcher.io) {
            when (val response = userRepository.doOTPSendCall(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        OtpResponse.Failure(response.message.toString())
                    )
                }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(OtpResponse.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(
                            OtpResponse.Success(
                                "Otp Sent to you mobile", response.data))
                    }
                }
            }
        }
    }
}

class OtpMobileViewModelFactory(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OtpMobileViewModel::class.java)) {
            return OtpMobileViewModel(
                userRepository, dispatcher
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
