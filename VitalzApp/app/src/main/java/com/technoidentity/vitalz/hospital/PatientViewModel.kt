package com.technoidentity.vitalz.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
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
        class Success(val resultText: String, val data: PatientDataList) : PatientData()
        class Failure(val errorText: String) : PatientData()
        object Loading : PatientData()
        object Empty : PatientData()
    }

    private val _expectedResult = MutableLiveData<PatientData>(
        PatientData.Empty)
    val expectedResult: LiveData<PatientData> = _expectedResult

    fun getPatientListData(mobile: String, hospitalId: String) {
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
                        _expectedResult.postValue(PatientData.Success("Patient List", response.data))
                    }
                }
            }
        }
    }
}
