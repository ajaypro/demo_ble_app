package com.technoidentity.vitalz.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    sealed class CareTaker {
        class Success(val resultText: String) : CareTaker()
        class Failure(val errorText: String) : CareTaker()
        object Loading : CareTaker()
        object Empty : CareTaker()
    }

    private val _expectedResult = MutableLiveData<CareTaker>(CareTaker.Empty)
    val expectedResult: LiveData<CareTaker> = _expectedResult

    fun getCareTakerResponse(mobile: String) {
        val request = CareTakerRequest()
        request.phoneNo = mobile
        viewModelScope.launch {
            _expectedResult.value = CareTaker.Loading
            when (val response = userRepository.doMobileOTPCall(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                    CareTaker.Failure("Contact Hospital"
                )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = CareTaker.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = CareTaker.Success("Otp Sent to you mobile")
                    }
                }
            }
        }
    }
}
