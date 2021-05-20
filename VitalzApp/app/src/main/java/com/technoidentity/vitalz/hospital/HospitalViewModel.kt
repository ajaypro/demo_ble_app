package com.technoidentity.vitalz.hospital

import android.util.Log
import androidx.lifecycle.*
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class HospitalData {
        class Success(val resultText: String, val data: HospitalListData) : HospitalData()
        class Failure(val errorText: String) : HospitalData()
        object Loading : HospitalData()
        object Empty : HospitalData()
    }

    private val _expectedResult = MutableLiveData<HospitalViewModel.HospitalData>(
        HospitalViewModel.HospitalData.Empty)
    val expectedResult: LiveData<HospitalViewModel.HospitalData> = _expectedResult

    fun getHospitalListData(token: String) {
        if (token == null) {
            Log.v("Check", "Stage_1 $token")
            _expectedResult.value = HospitalData.Failure("Token Expired")
            return
        }
        val request =
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(HospitalData.Loading)
            when (val response = userRepository.getHospitalList()) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        HospitalData.Failure(response.message.toString())
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(HospitalData.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(HospitalData.Success("Otp Sent to you mobile", response.data))
                    }
                }
            }
        }
    }
}

class HospitalViewModelFactory(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HospitalViewModel::class.java)) {
            return HospitalViewModel(
                userRepository, dispatcher
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}