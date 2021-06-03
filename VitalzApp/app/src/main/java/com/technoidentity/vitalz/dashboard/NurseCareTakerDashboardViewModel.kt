package com.technoidentity.vitalz.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NurseCareTakerDashboardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class SinglePatient {
        class Success(val resultText: String, val data: SinglePatientDashboardResponse) : SinglePatient()
        class Failure(val errorText: String) : SinglePatient()
        object Loading : SinglePatient()
        object Empty : SinglePatient()
    }

    private val _expectedResult = MutableLiveData<SinglePatient>(
        SinglePatient.Empty)
    val expectedResult: LiveData<SinglePatient> = _expectedResult

    fun getSinglePatientData(patientId: String) {
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(SinglePatient.Loading)
            when (val response = userRepository.getSinglePatientDashboardList(patientId)) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        SinglePatient.Failure(response.message.toString())
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(SinglePatient.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(SinglePatient.Success(resultText = "Single Patient Dashboard Data",data = response.data))
                    }
                }
            }
        }
    }
}
