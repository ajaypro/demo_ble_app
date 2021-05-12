package com.technoidentity.vitalz.user

import androidx.lifecycle.*
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
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

    sealed class CareTaker {
        class Success(val resultText: String) : CareTaker()
        class Failure(val errorText: String) : CareTaker()
        object Loading : CareTaker()
        object Empty : CareTaker()
    }

    private val _expectedResult = MutableLiveData<CareTaker>(CareTaker.Empty)
    val expectedResult: LiveData<CareTaker> = _expectedResult

    fun getCareTakerResponse(mobile: String) {
        if (mobile == null) {
            _expectedResult.value = CareTaker.Failure("Not a Valid Number")
            return
        }
        val request = CareTakerRequest()
        request.phoneNo = mobile
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(CareTaker.Loading)
            when (val response = userRepository.doMobileOTPCall(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                    CareTaker.Failure(response.message.toString())
                )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(CareTaker.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(CareTaker.Success("Otp Sent to you mobile"))
                    }
                }
            }
        }
    }

    fun getOtpResponse(mobile: String?, otpReceived: Int) {
        if (mobile == null && otpReceived == null) {
            _expectedResult.value = CareTaker.Failure("Not a Valid Number")
            return
        }
        val request = OtpRequest()
        request.phoneNo = mobile
        request.otp = otpReceived
        viewModelScope.launch(dispatcher.io){
            when (val response = userRepository.doOTPSendCall(request)){
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        CareTaker.Failure(response.message.toString())
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(CareTaker.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(CareTaker.Success("Otp Sent to you mobile"))
                    }
                }
            }
        }
    }
}

class CareTakerMobileViewModelFactory(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CareTakerMobileViewModel::class.java)) {
            return CareTakerMobileViewModel(
                userRepository, dispatcher
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
