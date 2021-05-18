package com.technoidentity.vitalz.hospital

import android.util.Log
import androidx.lifecycle.*
import com.technoidentity.vitalz.data.datamodel.patient.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient.PatientRequest
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class PatientData {
        class Success(val resultText: String, val data: PatientDataList?) : PatientData()
        class Failure(val errorText: String) : PatientData()
        object Loading : PatientData()
        object Empty : PatientData()
    }

    private val _expectedResult = MutableLiveData<PatientData>(
        PatientData.Empty)
    val expectedResult: LiveData<PatientData> = _expectedResult

    fun getPatientListData(mobile: String, hospitalId: String) {
        if (mobile == null) {
            Log.v("Check", "Stage_1 $mobile")
            _expectedResult.value = PatientData.Failure("Mobile No not found")
            return
        }
        val request = PatientRequest()
        request.hospitalId = hospitalId
        request.phoneNo = mobile
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(PatientData.Loading)
            when (val response = userRepository.getPatientList(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        PatientData.Failure(response.message.toString())
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(PatientData.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(PatientData.Success("Otp Sent to you mobile", response.data))
                    }
                }
            }
        }
    }
}

class PatientViewModelFactory(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            return PatientViewModel(
                userRepository, dispatcher
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}